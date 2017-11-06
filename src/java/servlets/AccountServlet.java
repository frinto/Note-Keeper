/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.DBUtil;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        
        if(user != null)
        {
           response.sendRedirect("notes");
           return;
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
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
                        
                        session.setAttribute("adminSession", u.getUsername());
                        
                        response.sendRedirect("admin");
                        
                        return;
                    } else if (u.getRole().getRoleID() == 2)
                    {
                        HttpSession session = request.getSession();
                        
                        User user = new User(u.getUsername(), u.getPassword(), u.getEmail(), u.getActive(), u.getFirstname(), u.getLastname());
                        
                        session.setAttribute("userSession", user);
                        session.setAttribute("loggedInUser", u.getUsername());
                        
                        response.sendRedirect("notes");
                        
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

    }

}
