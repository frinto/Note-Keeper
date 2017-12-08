/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.RoleService;
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
@WebServlet(name = "ManageRoleServlet", urlPatterns =
{
    "/ManageRoleServlet"
})
public class ManageRoleServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("adminSession");

        RoleService rs = new RoleService();

        String action = request.getParameter("action");

        if (action != null && action.equals("view"))
        {
            String selectedRoleID = request.getParameter("selectedRoleID");
            try
            {
                Role role = rs.get(Integer.parseInt(selectedRoleID));
                request.setAttribute("selectedRole", role);
            } catch (Exception ex)
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Role> roles = null;

        try
        {
            roles = rs.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("roles", roles);

        if (action != null && action.equals("logout"))
        {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else if (action != null && action.equals("view"))
        {
            getServletContext().getRequestDispatcher("/WEB-INF/manageRole.jsp").forward(request, response);
            return;
        } else if (action == null && admin != null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/manageRole.jsp").forward(request, response);
            return;
        } else
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

        String roleID = request.getParameter("roleID");
        String roleName = request.getParameter("roleName");

        String action = request.getParameter("action");

        RoleService rs = new RoleService();

        if (action.equals("add"))
        {
            if (roleName.equals(""))
            {
                request.setAttribute("errorMessage", "please fill in all fields");

            } else
            {
                try
                {
                    rs.insert(Integer.parseInt(roleID),roleName);
                } catch (Exception ex)
                {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (action.equals("delete"))
        {
            int selectedRoleID = Integer.parseInt(request.getParameter("selectedRole"));
            try
            {
                rs.delete(selectedRoleID);
            } catch (Exception ex)
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equals("edit"))
        {
            Role role = new Role(Integer.parseInt(roleID), roleName);
            rs.update(role.getRoleID(), role.getRoleName());
        }

        List<Role> roles = null;

        try
        {
            roles = rs.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(ManageRoleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("roles", roles);
        getServletContext().getRequestDispatcher("/WEB-INF/manageRole.jsp").forward(request, response);

    }

}
