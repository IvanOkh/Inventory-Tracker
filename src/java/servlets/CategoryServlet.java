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
import models.Category;
import models.User;
import services.AccountService;
import services.CategoryService;

public class CategoryServlet extends HttpServlet 
{
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("currentlyLoged");
        
        
        AccountService accServ = new AccountService();
        User y = accServ.getUser(email);
        CategoryService catServ = new CategoryService();
        
        
        
        if (email == null)
        {
            response.sendRedirect("login");
        }
        
        if (y.getRole().getRoleName().equalsIgnoreCase("system admin") 
                || y.getRole().getRoleName().equalsIgnoreCase("company admin") )
        {
            request.setAttribute("show", email);

            //List <User> userList = accServ.getUsers();
            List <Category> catList = catServ.getCategories();
            
            request.setAttribute ("displayCategories", catList);
            
            String edit = request.getParameter("edit");
            
            if(edit != null)
            {
                Category g = catServ.getCategory(Integer.valueOf(edit));
                request.setAttribute("category", g);
            
                request.setAttribute("control", "Edit");
            
                request.setAttribute("test", edit);
            }
            else
            {
                request.setAttribute("control", "Add");
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(request, response);
        }
        
        response.sendRedirect("login");
          
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        CategoryService catServ = new CategoryService();
        
        String action = request.getParameter("option");

        String categoryBox = request.getParameter("categoryBox");
        String catName = request.getParameter("categoryName");

        
        try
        {
            if(action.equals("Save"))
            {
                String categoryID = request.getParameter("edit");
                catServ.update(Integer.valueOf(categoryID), categoryBox);
                              
            }
            else if(action.equals("Add"))
            {
                catServ.insert(catName);
                //accServ.insert(email, fname, lname, password);
            }

        }
        catch(Exception e)
        {
            
        }

        response.sendRedirect("category");           
    }
    
}
