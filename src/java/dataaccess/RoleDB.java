/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domainmodel.Role;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Administrator
 */
public class RoleDB
{
     public static Role getRole(int roleID)throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Role role = em.find(Role.class, roleID);
            return role;
        } catch (Exception ex) {
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, "Cannot read Role", ex);
            throw new DBException("Error getting role");
        } finally {
            em.close();
        }
    }

    public static List<Role> getAll() throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try
        {
            List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        } catch (Exception ex)
        {
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, "Cannot read roles", ex);
            throw new DBException("Error getting role");
        } finally
        {
            em.close();
        }
    }

    public static int delete(Role deletedRole)throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(deletedRole));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, "Cannot delete " + deletedRole.toString(), ex);
            throw new DBException("Error deleting role");
        } finally {
            em.close();
        }
    }

    public static int insert(Role role)throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(role);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, "Cannot insert " + role.toString(), ex);
            throw new DBException("Error inserting role");
        } finally {
            em.close();
        }
    }

    public static int update(Role role)throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(role);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(RoleDB.class.getName()).log(Level.SEVERE, "Cannot update " + role.toString(), ex);
            throw new DBException("Error updating company");
        } finally {
            em.close();
        }
    }
}
