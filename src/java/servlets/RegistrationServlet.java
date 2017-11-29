/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.UserService;
import dataaccess.DBUtil;
import domainmodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "RegistrationServlet", urlPatterns =
{
    "/RegistrationServlet"
})
public class RegistrationServlet extends HttpServlet
{

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String companyID = request.getParameter("company");
        
        int companyIDInt = Integer.parseInt(companyID);
        
        UserService us = new UserService();
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

            String qString = "select u from User u where u.username in (:username)";

            TypedQuery<User> q = em.createQuery(qString, User.class);
            q.setParameter("username", username);

            List<User> users = q.getResultList();

            if (users.size() != 0)
            {
                request.setAttribute("errorMessage", "ERROR Username is already taken");
                getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
                return;
            }

            String qString2 = "select u from User u where u.email in (:email)";

            TypedQuery<User> z = em.createQuery(qString2, User.class);
            z.setParameter("email", email);

            List<User> users2 = z.getResultList();

            if (users2.size() != 0)
            {
                request.setAttribute("errorMessage", "ERROR email is already taken");
                getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
                return;
            }
        
        try
        {
            us.insert(username, password, email, true, firstName, lastName, 2, companyIDInt);
            request.setAttribute("username", username);
            getServletContext().getRequestDispatcher("/WEB-INF/successReg.jsp").forward(request,response);
            return;
        } catch (Exception ex)
        {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
