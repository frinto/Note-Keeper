/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domainmodel.Note;
import domainmodel.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Administrator
 */
public class NoteDB
{

    public static List<Note> getAll(User owner) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        
        String qString = "select n from Note n where n.owner in (:owner)";

        TypedQuery<Note> q = em.createQuery(qString, Note.class);
        q.setParameter("owner", owner);
        
        List<Note> notes = q.getResultList();
        

        try
        {
            //List<Note> notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();
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

    public static Note getNote(int noteID) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, noteID);
            return note;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new DBException("Error getting Notes");
        } finally {
            em.close();
        }
    }
    
    public static int delete(Note note) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(note));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new DBException("Error deleting note");
        } finally {
            em.close();
        }
    }

    public static int insert(Note note) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(note);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            throw new DBException("Error inserting note");
        } finally {
            em.close();
        }
    }

    public static int update(Note note) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new DBException("Error updating note");
        } finally {
            em.close();
        }
    }

}
