/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDBException;
import java.util.List;
import models.Category;


public class CategoryService 
{
    public List<Category> getCategories()
    {
        CategoriesDB cdb = new CategoriesDB();
        List <Category> catList = cdb.getAll();
        return catList;
    }
    
    public void update(int id, String categ) throws Exception 
    {
        CategoriesDB cdb = new CategoriesDB();

        Category f = cdb.getCategoryByID(id);
        f.setCategoryName(categ);

        cdb.update(f);       
    }
    
    public Category getCategory (int id)
    {
        CategoriesDB cdb = new CategoriesDB();
        Category h = cdb.getCategoryByID(id);
        
        return h;
    }
    
     public int insert (String name) throws Exception
    {
        CategoriesDB cdb = new CategoriesDB();
      
        Category g = new Category();
        g.setCategoryName(name);
        g.setCategoryID(0);

        int r = cdb.insert(g);
        
        if(r == 0)
        {
            throw new ItemsDBException("Error: Can't insert new category ;(");
        }
        
        return r;
    }
}
