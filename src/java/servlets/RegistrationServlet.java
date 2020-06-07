/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;


public class RegistrationServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("currentlyLoged");
        
        if (email != null) 
        {
            request.setAttribute("control_R", "cant_register");
        }
        else
        {
            request.setAttribute("control_R", "do_register");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String loged = (String) session.getAttribute("currentlyLoged");
        AccountService accServ = new AccountService();

        //String email, String fname, String lname, String password
        String action = request.getParameter("option");

        String email = request.getParameter("emailBox");
        String fname = request.getParameter("fnameBox");
        String lname = request.getParameter("lnameBox");
        String password = request.getParameter("passwordBox");
        
        try 
        {
            if (action.equals("Add")) 
            {
                //if(loged == null )
                //{
                    accServ.insert(email, fname,  lname, password);
                    User userData = accServ.login(email, password);
                    session.setAttribute("currentlyLoged", userData.getEmail());
                    
                //}           
            } 

        } catch (Exception e) 
        {
            request.setAttribute("errorMsg", e);
        }

        //response.sendRedirect("register");
        response.sendRedirect("inventory");
    
    }
}
