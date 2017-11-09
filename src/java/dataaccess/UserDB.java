/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domainmodel.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Administrator
 */
public class UserDB
{

    public static List<User> getAll() throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try
        {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } catch (Exception ex)
        {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new DBException("Error getting user");
        } finally
        {
            em.close();
        }
    }

    public static int insert(User user) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(user);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot insert " + user.toString(), ex);
            throw new DBException("Error inserting user");
        } finally {
            em.close();
        }
    }
    
    public static User getUser(String username) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, username);
            return user;
        } catch (Exception ex) {
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new DBException("Error getting Users");
        } finally {
            em.close();
        }
    }
    
    public static int delete(User user) throws DBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, "Cannot delete " + user.toString(), ex);
            throw new DBException("Error deleting user");
        } finally {
            em.close();
        }
    }

    public static int update(User user) throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(user);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, "Cannot update " + user.toString(), ex);
            throw new DBException("Error updating user");
        } finally {
            em.close();
        }
    }

   
}
