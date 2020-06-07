/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;


public class AdminFilter implements Filter 
{
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                            FilterChain chain) throws IOException, ServletException 
    {
        // this code will execute before HomeServlet and UsersServlet
        HttpServletRequest r = (HttpServletRequest)request;
        HttpSession session = r.getSession();
        
        String email = (String) session.getAttribute("currentlyLoged");
        AccountService us = new AccountService();
        User u = new User();
        try 
        {
            u = us.getUser(email);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
        if(u.getActive()== true)
        {           

            chain.doFilter(request, response);
        } 
        else 
        {
            // so, send them to login page
            session.invalidate();
            HttpServletResponse resp = (HttpServletResponse)response;
            resp.sendRedirect("login");
        }     
        // this code will execute after HomeServlet and UsersServlet
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
    
}
