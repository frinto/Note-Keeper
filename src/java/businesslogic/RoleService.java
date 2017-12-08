/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.DBException;
import dataaccess.RoleDB;
import domainmodel.Role;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class RoleService
{
     public Role get(int roleID) throws Exception {
        return RoleDB.getRole(roleID);
    }
    
    public List<Role> getAll() throws Exception
    {
        return RoleDB.getAll();
    }
    
    public int delete(int roleID) throws Exception
    {
        Role deletedRole = RoleDB.getRole(roleID); 
        return RoleDB.delete(deletedRole);
    }
    
    public int insert(int roleID, String roleName) throws Exception {
        Role role = new Role(roleID,roleName);
        return RoleDB.insert(role);
    }

    public int update(int roleID, String roleName)
    {
        Role role = new Role(roleID,roleName);
        try
        {
            return RoleDB.update(role);
        } catch (DBException ex)
        {
            Logger.getLogger(RoleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
