/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.UserDB;
import domainmodel.Role;
import domainmodel.User;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class UserService
{

    public List<User> getAll() throws Exception
    {
        return UserDB.getAll();
    }

    public int insert(String username, String password, String email, boolean activeBoolean, String firstname, String lastname, int roleID) throws Exception
    {
        Role role = new Role(roleID);
        
        User user = new User(username, password, email, activeBoolean, firstname, lastname, role);
        return UserDB.insert(user);
    }
    
}
