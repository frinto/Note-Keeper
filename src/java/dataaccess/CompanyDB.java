/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import domainmodel.Company;
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
public class CompanyDB
{

    public static Company getCompany(int companyID)throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Company company = em.find(Company.class, companyID);
            return company;
        } catch (Exception ex) {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot read company", ex);
            throw new DBException("Error getting company");
        } finally {
            em.close();
        }
    }

    public static List<Company> getAll() throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try
        {
            List<Company> companys = em.createNamedQuery("Company.findAll", Company.class).getResultList();
            return companys;
        } catch (Exception ex)
        {
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot read companys", ex);
            throw new DBException("Error getting user");
        } finally
        {
            em.close();
        }
    }

    public static int delete(Company deletedCompany)throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.remove(em.merge(deletedCompany));
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot delete " + deletedCompany.toString(), ex);
            throw new DBException("Error deleting company");
        } finally {
            em.close();
        }
    }

    public static int insert(Company company)throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(company);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot insert " + company.toString(), ex);
            throw new DBException("Error inserting company");
        } finally {
            em.close();
        }
    }

    public static int update(Company company)throws DBException
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(company);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(CompanyDB.class.getName()).log(Level.SEVERE, "Cannot update " + company.toString(), ex);
            throw new DBException("Error updating company");
        } finally {
            em.close();
        }
    }
    
}
