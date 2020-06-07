/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Item;
import models.User;
import services.AccountService;
import services.InventoryService;


public class AdminServlet extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("currentlyLoged");
        
        
        AccountService accServ = new AccountService();
        User y = accServ.getUser(email);
        InventoryService invServ = new InventoryService();
      
        if (email == null)
        {
            response.sendRedirect("login");
        }
        
        if (y.getRole().getRoleName().equalsIgnoreCase("system admin") 
                      || y.getRole().getRoleName().equalsIgnoreCase("company admin") )
        {
            
            try{
            List <Item> itm = (List <Item>)session.getAttribute("searchList");
            request.setAttribute ("displayItems", itm);
            }
            catch (Exception e)
            {
                
            }
            
            
            
            request.setAttribute("show", email);

            List <User> userList = accServ.getUsers();
            
            request.setAttribute ("displayUsers", userList);
            
            String edit = request.getParameter("edit");
            String delete = request.getParameter("delete");
            String reactivate = request.getParameter("activate");
            
            if(edit != null)
            {
                User n = accServ.getUser(String.valueOf(edit));
                request.setAttribute("user", n);
            
                request.setAttribute("control", "Edit");
            
                request.setAttribute("test", edit);
            }
            else if (delete != null)
            {
                User n = accServ.getUser(String.valueOf(delete));
                request.setAttribute("user", n);
            
                request.setAttribute("control", "Delete");
            
                request.setAttribute("test", delete);
            }
            else
            {
                request.setAttribute("control", "Add");
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
        
        response.sendRedirect("login");
          
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        AccountService accServ = new AccountService();
        InventoryService invServ = new InventoryService();
        HttpSession session = request.getSession();
        
        String action = request.getParameter("option");

        String email = request.getParameter("emailBox");
        String fname = request.getParameter("fnameBox");
        String lname = request.getParameter("lnameBox");
        String password = request.getParameter("passwordBox");
        
        try
        {
            if(action.equals("Save"))
            {
                String edit = request.getParameter("edit");
                accServ.update(String.valueOf(edit), fname, lname, password);
                              
            }
            else if(action.equals("Add"))
            {
                accServ.insert(email, fname, lname, password);
            }
            else if(action.equals("Activate"))
            {
                String activate = request.getParameter("activate");
                accServ.reactivate(activate);
            }
            else if(action.equals("Delete"))
            {
                String edit = request.getParameter("delete");
                accServ.delete(edit);
            }
            else if(action.equals("Search"))
            {
                String srch = request.getParameter("searchBox");
               
                List <Item> items = invServ.getAllItems(srch);
                session.setAttribute ("searchList", items);
                request.setAttribute ("displayItems", items);
            }
        }
        catch(Exception e)
        {
            
        }

        response.sendRedirect("admin");           
    }
 
}
