/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;


public class AccountServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("currentlyLoged");
        
        AccountService accServ = new AccountService();
        User y = accServ.getUser(email);
              
        if (email == null)
        {
            response.sendRedirect("inventory");
        }
        
        else
        {
            if (y.getRole().getRoleName().equalsIgnoreCase("system admin")
                    || y.getRole().getRoleName().equalsIgnoreCase("company admin"))
            {           
                request.setAttribute("isUser", false);
            }
            else
            {
                request.setAttribute("isUser", true);
            }
            
            request.setAttribute("show", email);
           
            request.setAttribute ("user", y);
            
            String edit = request.getParameter("edit");
            String delete = request.getParameter("delete");
            
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
            
            getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
        }
        
        response.sendRedirect("login");         
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("currentlyLoged");
        AccountService accServ = new AccountService();
        
        String action = request.getParameter("option");

        //String Eemail = request.getParameter("emailBox");
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

            else if(action.equals("Delete"))
            {
                String edit = request.getParameter("delete");
                if (email.equalsIgnoreCase(edit))
                {
                    accServ.deactivate(edit);
                }
            }
        }
        catch(Exception e)
        {
            
        }

        response.sendRedirect("account");           
    }
}
