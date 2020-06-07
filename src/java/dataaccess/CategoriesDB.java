package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;

public class CategoriesDB 
{
    public List<Category> getAll()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            List<Category> tempList = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return tempList;
        }
        finally
        {
            em.close();
        }
    }
        
    public Category getCategory (String categoryname)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            
            Category n = em.createNamedQuery("Category.findByCategoryName", Category.class)
                    .setParameter("categoryName", categoryname).getSingleResult();
            return n;
        }
        finally
        {
            em.close();
        }
        
    } 
    
    public Category getCategoryByID (int id)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try
        {
            
            Category n = em.createNamedQuery("Category.findByCategoryID", Category.class)
                    .setParameter("categoryID", id).getSingleResult();
            return n;
        }
        finally
        {
            em.close();
        }
        
    }  
     
    public int update(Category f)
    {
        int r = 0;       
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            trans.begin();
            em.merge(f);
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
    
    public int insert(Category g)
    {
        int r = 0;              
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try 
        {
            trans.begin();
            em.persist(g);
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
