/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDBException;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;


public class AccountService 
{
    
public int delete(String email) throws Exception
{
        UserDB udb = new UserDB();
        User n = udb.get(email);
        int r=0;
        
        if (n.getRole().getRoleName().equalsIgnoreCase("system admin") 
                      || n.getRole().getRoleName().equalsIgnoreCase("company admin") )
        {
            throw new UserDBException("Error: Can't delete an admin ;(");
        }
        else
        {
            r = udb.delete(n);
            if(r == 0)
            {
                throw new UserDBException("Error: Can't delete ;(");
            }
        }
         
        return r;
    }

    public int insert (String email, String fname, String lname, String password) throws Exception
    {
        UserDB udb = new UserDB();
        boolean active = true;
        Role role = new Role (2, "regular user");     
        User n = new User(email,active,fname,lname,password);
        n.setRole(role);
        int r = udb.insert(n);
        
        if(r == 0)
        {
            throw new UserDBException("Error: Can't insert ;(");
        }
        
        return r;
    }
    public int update(String email, String fname, String lname, String password) throws Exception
    {
        UserDB udb = new UserDB();
        User user = udb.get(email);     
        boolean active = user.getActive();
        Role role = user.getRole();
        
        User n = new User(email,active,fname,lname,password);
        n.setRole(role);
        n.setItemList(user.getItemList());
        int r = udb.update(n);
        
        if(r == 0)
        {
            throw new UserDBException("Error: Can't update ;(");
        }
        
        return r;
    }
    
    public int deactivate(String email) throws Exception
    {
        UserDB udb = new UserDB();
        User user = udb.get(email);
        boolean active = false;
        Role role = user.getRole();
        
        //User n = new User(email,active,fname,lname,password);
        user.setActive(active);
        int r = udb.update(user);
        
        if(r == 0)
        {
            throw new UserDBException("Error: Can't deactivate ;(");
        }
        
        return r;
    }
    
    public int reactivate(String email) throws Exception
    {
        UserDB udb = new UserDB();
        User user = udb.get(email);
        boolean active = true;
        Role role = user.getRole();
        
        //User n = new User(email,active,fname,lname,password);
        user.setActive(active);
        int r = udb.update(user);
        
        if(r == 0)
        {
            throw new UserDBException("Error: Can't reactivate ;(");
        }
        
        return r;
    }
    
    public User getUser (String email)
    {
        UserDB udb = new UserDB();
        User n = udb.get(email);
        return n;
    }
    
    public List<User> getUsers()
    {
        UserDB udb = new UserDB();
        List <User> userList = udb.getAll();
        return userList;
    }
    
    public User login(String email, String password)
    {
        User n = new User();
        n.getItemList();
        UserDB udb = new UserDB();
        try
        {
            n = udb.get(email);
                      
            if (n.getActive()== true && email.equalsIgnoreCase(n.getEmail()) && password.equals(n.getPassword()))
            {
                return n;
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return null;
    }
}
