/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.DBException;
import dataaccess.NoteDB;
import dataaccess.UserDB;
import domainmodel.Company;
import domainmodel.Role;
import domainmodel.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class UserService
{
    public User get(String username) throws Exception {
        return UserDB.getUser(username);
    }

    public List<User> getAll() throws Exception
    {
        return UserDB.getAll();
    }

    public int insert(String username, String password, String email, boolean activeBoolean, String firstname, String lastname, int roleID, int companyID) throws Exception
    {
        Role role = new Role(roleID);
        Company company =  new Company(companyID);
        
        User user = new User(username, password, email, activeBoolean, firstname, lastname, role, company);
        return UserDB.insert(user);
    }

    public int delete(String selectedUsername)
    {
        try
        {
            User deletedUser = UserDB.getUser(selectedUsername);
            return UserDB.delete(deletedUser);
        } catch (DBException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int update(User user)
    {
        try
        {
            return UserDB.update(user);
        } catch (DBException ex)
        {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
}
