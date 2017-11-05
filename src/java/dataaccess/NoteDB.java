/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domainmodel.Note;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Administrator
 */
public class NoteDB
{

    public static List<Note> getAll() throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try
        {
            List<Note> notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return notes;
        } catch (Exception ex)
        {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new DBException("Error getting Notes");
        } finally
        {
            em.close();
        }
    }

}
