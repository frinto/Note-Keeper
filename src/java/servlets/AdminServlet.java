/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
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

        UserService us = new UserService();

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

            us.delete(selectedUsername);

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
