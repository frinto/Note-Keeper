/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import businesslogic.NoteService;
import domainmodel.Note;
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
@WebServlet(name = "NoteServlet", urlPatterns =
{
    "/NoteServlet"
})
public class NoteServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        
        NoteService ns = new NoteService();
        
        String action = request.getParameter("action");
        if (action != null && action.equals("view")) {
            String selectedNoteId = request.getParameter("selectedNoteId");
            try {
                Note note = ns.get(Integer.parseInt(selectedNoteId));
                request.setAttribute("selectedNote", note);
            } catch (Exception ex) {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        
        List<Note> notes = null;

        try
        {
            notes = ns.getAll(user);
        } catch (Exception ex)
        {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        request.setAttribute("notes", notes);
        
        if(action != null && action.equals("logout"))
        {
            session.invalidate();
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }else if(action != null && action.equals("view"))
        {
            getServletContext().getRequestDispatcher("/WEB-INF/notes/notes.jsp").forward(request, response);
            return;
        }
        else if(action == null && user != null)
        {
            getServletContext().getRequestDispatcher("/WEB-INF/notes/notes.jsp").forward(request, response);
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
        String noteID = request.getParameter("noteID");
        String action = request.getParameter("action");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        
        NoteService ns = new NoteService();
        
        if(action.equals("delete"))
        {
            int selectedNoteID = Integer.parseInt(request.getParameter("selectedNote"));
            try
            {
                ns.delete(selectedNoteID);
            } catch (Exception ex)
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equals("add"))
        {
            try
            {
                ns.insert(title, contents, user);
            } catch (Exception ex)
            {
                Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(action.equals("edit"))
        {
            Note note = new Note(Integer.parseInt(noteID),title, contents, user);
            ns.update(note.getNoteID(),note.getTitle() ,note.getContents(), user);
        }
        
        List<Note> notes = null;

        try
        {
            notes = ns.getAll(user);
        } catch (Exception ex)
        {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("notes", notes);
        getServletContext().getRequestDispatcher("/WEB-INF/notes/notes.jsp").forward(request, response);

    }

}
