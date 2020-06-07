/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;


public class UserDB 
{
    public int insert(User user)
    {
        int r = 0;              
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            trans.begin();
            em.persist(user);
            trans.commit();
            
            r = 1;                   
            return r;
        } 
        catch (Exception e) 
        {
            trans.rollback();
            return r;
        } 
        finally 
        {
            em.close();
        }
    }
    
    public int update(User user)
    {
        int r = 0;       
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            trans.begin();
            em.merge(user);
            trans.commit();
            
            r = 1;                   
            return r;
        } 
        catch (Exception e) 
        {
            trans.rollback();
            return r;
        } 
        finally 
        {
            em.close();
        }
    }
    
    public List<User> getAll()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            boolean act = true;
            List<User> tempList = em.createNamedQuery("User.findAll", User.class).getResultList();
            //List<User> tempList = em.createNamedQuery("User.findByActive", User.class)
                           // .setParameter("active", act).getResultList();

            return tempList;
        }
        finally
        {
            em.close();
        }
    }
    
    public User get(String email)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            User n = em.find(User.class, email);
            return n;
        }
        finally
        {
            em.close();
        }
    }
    
    public int delete(User user)
    {
        int r = 0;              
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            user.setActive(false);
            trans.begin();
            em.remove(em.merge(user));
            //em.merge(user);
            
            trans.commit();
            
            r = 1;        
            return r;
        } 
        catch (Exception e) 
        {
            trans.rollback();
            return r;
        } 
        finally 
        {
            em.close();
        }
    }
}
