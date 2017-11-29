/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.CompanyService;
import domainmodel.Company;
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
@WebServlet(name = "ManageCompanyServlet", urlPatterns =
{
    "/ManageCompanyServlet"
})
public class ManageCompanyServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute("adminSession");

        CompanyService cs = new CompanyService();

        String action = request.getParameter("action");

        if (action != null && action.equals("view"))
        {
            String selectedCompanyID = request.getParameter("selectedCompanyID");
            try
            {
                Company company = cs.get(Integer.parseInt(selectedCompanyID));
                request.setAttribute("selectedCompany", company);
            } catch (Exception ex)
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Company> comapanys = null;

        try
        {
            comapanys = cs.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("companys", comapanys);

        if (action != null && action.equals("logout"))
        {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else if (action != null && action.equals("view"))
        {
            getServletContext().getRequestDispatcher("/WEB-INF/manageCompany.jsp").forward(request, response);
            return;
        } else if (action == null && admin != null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/manageCompany.jsp").forward(request, response);
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

        String companyID = request.getParameter("companyID");
        String companyName = request.getParameter("companyName");

        String action = request.getParameter("action");

        CompanyService cs = new CompanyService();

        if (action.equals("add"))
        {
            if (companyName.equals(""))
            {
                request.setAttribute("errorMessage", "please fill in all fields");

            } else
            {
                try
                {
                    cs.insert(companyName);
                } catch (Exception ex)
                {
                    Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (action.equals("delete"))
        {
            int selectedCompanyID = Integer.parseInt(request.getParameter("selectedCompany"));
            try
            {
                cs.delete(selectedCompanyID);
            } catch (Exception ex)
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (action.equals("edit"))
        {
            Company company = new Company(Integer.parseInt(companyID), companyName);
            cs.update(company.getCompanyID(), company.getCompanyName());
        }

        List<Company> companys = null;

        try
        {
            companys = cs.getAll();
        } catch (Exception ex)
        {
            Logger.getLogger(ManageCompanyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("companys", companys);
        getServletContext().getRequestDispatcher("/WEB-INF/manageCompany.jsp").forward(request, response);

    }

}
