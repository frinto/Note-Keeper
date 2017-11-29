/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
import dataaccess.DBUtil;
import domainmodel.Company;
import domainmodel.Role;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccountServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        User admin = (User) session.getAttribute("adminSession");

        String action = request.getParameter("action");

        UserService us = new UserService();

        if (user != null && action != null && action.equals("account"))
        {
            User updatedUser = (User) session.getAttribute("userView");
            session.setAttribute("userView", updatedUser);
            
            getServletContext().getRequestDispatcher("/WEB-INF/account/view.jsp").forward(request, response);
            return;
        }

        if (user != null && action != null && action.equals("viewUser"))
        {
            if (action != null && action.equals("viewUser"))
            {
                String selectedUsername = request.getParameter("selectedUser");
                try
                {
                    User selectedUser = us.get(selectedUsername);

                    boolean isActive = user.getActive();
                    String active;

                    if (isActive)
                    {
                        active = "1";
                    } else
                    {
                        active = "0";
                    }

                    Role role = selectedUser.getRole();
                    Company company = selectedUser.getCompany();
                    
                    Integer roleID = role.getRoleID();
                    String roleString = roleID.toString();
                    
                    Integer companyID = company.getCompanyID();
                    String companyString = companyID.toString();
                    
                    session.setAttribute("selectedUserCompany", companyString);
                    session.setAttribute("selectedUserRole", roleString);
                    session.setAttribute("selectedUserActive", active);
                    session.setAttribute("selectedUser", selectedUser);
                } catch (Exception ex)
                {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            getServletContext().getRequestDispatcher("/WEB-INF/account/edit.jsp").forward(request, response);
            return;
        }

        if (user != null)
        {
            response.sendRedirect("notes");
            return;
        }
        if (admin != null)
        {
            response.sendRedirect("admin");
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String action = request.getParameter("action");

        if (action != null && action.equals("login"))
        {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            EntityManager em = DBUtil.getEmFactory().createEntityManager();

            String qString = "select u from User u";

            TypedQuery<User> q = em.createQuery(qString, User.class);

            List<User> users = q.getResultList();

            for (User u : users)
            {
                if (u.getUsername().equals(username) && u.getPassword().equals(password))
                {
                    if (u.getActive() == true)
                    {
                        if (u.getRole().getRoleID() == 1)
                        {
                            HttpSession session = request.getSession();
                            
                            User admin = new User(u.getUsername(), u.getPassword(), u.getEmail(), u.getActive(), u.getFirstname(), u.getLastname(), u.getRole(), u.getCompany());

                            session.setAttribute("adminSession", admin);
                            session.setAttribute("loggedInAdmin", u.getUsername());

                            response.sendRedirect("admin");

                            return;
                        } else if (u.getRole().getRoleID() == 2)
                        {
                            HttpSession session = request.getSession();

                            User user = new User(u.getUsername(), u.getPassword(), u.getEmail(), u.getActive(), u.getFirstname(), u.getLastname(), u.getRole(), u.getCompany());

                            session.setAttribute("userView", user);
                            session.setAttribute("userSession", user);
                            session.setAttribute("loggedInUser", u.getUsername());

                            response.sendRedirect("notes");

                            return;
                        }else if (u.getRole().getRoleID() == 3)
                        {
                            HttpSession session = request.getSession();
                            
                            User admin = new User(u.getUsername(), u.getPassword(), u.getEmail(), u.getActive(), u.getFirstname(), u.getLastname(), u.getRole(), u.getCompany());

                            session.setAttribute("adminSession", admin);
                            session.setAttribute("loggedInAdmin", u.getUsername());

                            response.sendRedirect("companyAdmin");

                            return;
                        }

                    } else if (u.getActive() == false)
                    {
                        request.setAttribute("errorMsg", "This account is not active!");
                        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                        return;
                    }

                }

            }
            request.setAttribute("errorMsg", "invalid login credentials");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else if (action != null && action.equals("editAccount"))
        {
            boolean activeBoolean = false;
            UserService us = new UserService();

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String active = request.getParameter("active");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String role = request.getParameter("role");
            String company = request.getParameter("company");
            int companyInt;
            int roleInt;
            int activeInt;

            companyInt = Integer.parseInt(company);
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
            Company companyz = new Company(companyInt);
            User user = new User(username, password, email, activeBoolean, firstname, lastname, rolez, companyz);
            us.update(user);

            HttpSession session = request.getSession();

            session.setAttribute("userView", user);
            session.setAttribute("selectedUser", user);
            session.setAttribute("selectedUserActive", active);
          
            request.setAttribute("editSuccess", "Account Updated and Updated DB");
            getServletContext().getRequestDispatcher("/WEB-INF/account/edit.jsp").forward(request, response);
            return;
        }

    }

}
