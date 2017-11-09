/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
import domainmodel.Role;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "AdminServlet", urlPatterns =
{
    "/AdminServlet"
})
public class AdminServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
        HttpSession session = request.getSession();
        String admin = (String) session.getAttribute("adminSession");

        UserService us = new UserService();

        String action = request.getParameter("action");
        if (action != null && action.equals("view"))
        {
            String selectedUsername = request.getParameter("selectedUser");
            try
            {
                User user = us.get(selectedUsername);

                boolean isActive = user.getActive();
                String active;

                if (isActive)
                {
                    active = "1";
                } else
                {
                    active = "0";
                }

                Role role = user.getRole();
                Integer roleID = role.getRoleID();
                String roleString = roleID.toString();

                request.setAttribute("selectedUserRole", roleString);
                request.setAttribute("selectedUserActive", active);
                request.setAttribute("selectedUser", user);
            } catch (Exception ex)
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<User> users = null;

        try
        {
            users = us.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("users", users);
        
        if(action != null && action.equals("logout"))
        {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }else if(action != null && action.equals("view"))
        {
            getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
            return;
        }
        else if(action == null && admin != null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);
            return;
        }
        else
        {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        boolean activeBoolean = false;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String active = request.getParameter("active");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String role = request.getParameter("role");
        int roleInt;
        int activeInt;

        String action = request.getParameter("action");

        UserService us = new UserService();

        if (action.equals("add"))
        {
            if (username.equals("") || password.equals("") || email.equals("") || firstname.equals("") || lastname.equals("") || active.equals("") || active.equals("") || role.equals(""))
            {
                request.setAttribute("errorMessage", "please fill in all fields");

            } else
            {
                roleInt = Integer.parseInt(role);
                activeInt = Integer.parseInt(active);

                if (activeInt == 1)
                {
                    activeBoolean = true;
                } else if (activeInt == 0)
                {
                    activeBoolean = false;
                }

                try
                {
                    us.insert(username, password, email, activeBoolean, firstname, lastname, roleInt);
                } catch (Exception ex)
                {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else if (action.equals("delete"))
        {
            String selectedUsername = request.getParameter("selectedUser");
            
            try
            {
                User user = us.get(selectedUsername);
                Role userRole = user.getRole();
                
                if(userRole.getRoleID() == 1)
                {
                    request.setAttribute("errorDelete", "ERROR Admins cannot be deleted!");
                }
                else
                {
                    us.delete(selectedUsername);
                }
            } catch (Exception ex)
            {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (action.equals("edit"))
        {
            roleInt = Integer.parseInt(role);
            activeInt = Integer.parseInt(active);

            if (activeInt == 1)
            {
                activeBoolean = true;
            } else if (activeInt == 0)
            {
                activeBoolean = false;
            }
            
            Role rolez = new Role(roleInt);
            User user = new User(username, password, email, activeBoolean, firstname, lastname, rolez);
            us.update(user);
        }

        List<User> users = null;

        try
        {
            users = us.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request, response);

    }

}
