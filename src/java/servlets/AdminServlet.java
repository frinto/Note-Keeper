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
        getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request,response);

    }

}
