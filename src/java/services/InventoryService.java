/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;
import dataaccess.ItemsDBException;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

public class InventoryService 
{
    public void update(String email, String categ, int itemID, String itemName, double price) throws Exception 
    {
        ItemsDB idb = new ItemsDB();
        User n = idb.get(email);
        Item t = idb.getItem(itemID);  
        CategoriesDB cdb = new CategoriesDB();
        Category f = cdb.getCategory(categ);
        
        if (n.getEmail().equalsIgnoreCase(t.getOwner().getEmail()))
        {
            t.setCategory(f);
            t.setItemName(itemName);
            t.setPrice(price);
            idb.update(t);
        }
    }
    public int delete(String itemID, String email) throws Exception
    {
        int r = 0;
        ItemsDB idb = new ItemsDB();
        User n = idb.get(email);
        Item t = idb.getItem(Integer.valueOf(itemID));
        if (n.getEmail().equalsIgnoreCase(t.getOwner().getEmail()))
        {
            r = idb.delete(t);
            
            if(r == 0)
            {
                throw new ItemsDBException("Error: Can't delete ;(");
            }
        }

        return r;
    }
    

    public int insert (String email, String categ, String itemName, double price) throws Exception
    {
        ItemsDB idb = new ItemsDB();
        User n = idb.get(email);
        
        CategoriesDB cdb = new CategoriesDB();
        Category f = cdb.getCategory(categ);
        
        Item g = new Item();
        g.setOwner(n);
        g.setCategory(f);
        g.setItemName(itemName);
        g.setPrice(price);
        g.setItemID(0);
 
        int r = idb.insert(g);
        
        if(r == 0)
        {
            throw new ItemsDBException("Error: Can't insert ;(");
        }
        
        return r;
    }
     
    
    public Item getItem (int itemId)
    {
        ItemsDB idb = new ItemsDB();
        Item n = idb.getItem(itemId);
        return n;
    }
    
    public List <Item> getAllItems (String toSearch)
    {
       // toSearch.toLowerCase();
        ItemsDB idb = new ItemsDB();       
        List <Item> itemList = idb.getAllItems();
        List <Item> itemList2 = null;
        
        for (int i=0;i<itemList.size();i++)
        {
            //if(u.getFirstname().toLowerCase().startsWith("a") ||
            if (itemList.get(i).getItemName().startsWith("b"))
            {
                itemList2.add(itemList.get(i));
            }
        }

        return itemList2;
        
    }
        
    public List <Item> getItems (String email)
    {
        ItemsDB idb = new ItemsDB();
        User n = idb.get(email);
        List <Item> itemList = idb.getItems(n);
        return itemList;
        
    }
    
    
    public List <Category> getCategory()
    {
        CategoriesDB cdb = new CategoriesDB();
        List <Category> catList = cdb.getAll();
        return catList;
    }
 
}
