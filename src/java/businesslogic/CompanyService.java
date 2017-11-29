/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.CompanyDB;
import dataaccess.DBException;
import domainmodel.Company;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CompanyService
{
    public Company get(int companyID) throws Exception {
        return CompanyDB.getCompany(companyID);
    }
    
    public List<Company> getAll() throws Exception
    {
        return CompanyDB.getAll();
    }
    
    public int delete(int companyID) throws Exception
    {
        Company deletedCompany = CompanyDB.getCompany(companyID); 
        return CompanyDB.delete(deletedCompany);
    }
    
    public int insert(String companyName) throws Exception {
        Company company = new Company(companyName);
        return CompanyDB.insert(company);
    }

    public int update(int companyID, String companyName)
    {
        Company company = new Company(companyID,companyName);
        try
        {
            return CompanyDB.update(company);
        } catch (DBException ex)
        {
            Logger.getLogger(CompanyService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
