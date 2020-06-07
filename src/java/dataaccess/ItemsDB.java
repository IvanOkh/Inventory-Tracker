/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Item;
import models.User;


public class ItemsDB 
{
    public void update(Item item) throws Exception 
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try 
        {
            trans.begin();
            em.merge(item);
            trans.commit();
        } 
        catch (Exception e) 
        {
            trans.rollback();
        } 
        finally 
        {
            em.close();
        }
    }
    
    public int insert(Item item)
    {
        int r = 0;              
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            User m = item.getOwner();
            m.getItemList().add(item);
           
            trans.begin();
            em.merge(m);
            em.persist(item);
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
    
    public Item getItem (int id)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            Item n = em.find(Item.class, id);
            return n;
        }
        finally
        {
            em.close();
        }
        
    }
    
    public List <Item> getAllItems ()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            List <Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
            //List<User> tempList = em.createNamedQuery("User.findByActive", User.class)
                           // .setParameter("active", act).getResultList();
            return items;
        }
        finally
        {
            em.close();
        }
    } 
    
    
    public List <Item> getItems (User user)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            List <Item> items =  user.getItemList();
            return items;
        }
        finally
        {
            em.close();
        }
    } 
    
    public int delete(Item item)
    {
        int r = 0;              
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            User m = item.getOwner();
            m.getItemList().remove(item);
            
            trans.begin();
            em.merge(m);
            
            em.remove(em.merge(item));
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
