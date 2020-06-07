package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.AccountService;
import services.InventoryService;


public class InventoryServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("currentlyLoged");

        //request.setAttribute("show", email);
     
        if (email == null) 
        {
            response.sendRedirect("login");
        } 
        else 
        {
            InventoryService invServ = new InventoryService();
            List<Item> itemList = invServ.getItems(email);
            AccountService accServ = new AccountService();
            User n = accServ.getUser(email);

            List<Category> categoryList = invServ.getCategory();

            request.setAttribute("categories", categoryList);

            session.setAttribute("currentlyUser", n.getFname() + " " + n.getLname());

            request.setAttribute("displayItems", itemList);
            
            String edit = request.getParameter("edit");
            
            if (n.getRole().getRoleName().equalsIgnoreCase("system admin")
                    || n.getRole().getRoleName().equalsIgnoreCase("company admin"))
            {           
                request.setAttribute("isUser", false);
            }
            else
            {
                request.setAttribute("isUser", true);
            }
            
            if(edit != null)
            {
                Item i = invServ.getItem(Integer.valueOf(edit));
                request.setAttribute("item", i);

                request.setAttribute("control", "Edit");

                request.setAttribute("test", edit);
            }
            else
            {
                request.setAttribute("control", "Add");
            }

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("currentlyLoged");

        InventoryService invServ = new InventoryService();

        String action = request.getParameter("option");

        String categ = request.getParameter("drop");
        String item = request.getParameter("itemBox");
        String price = request.getParameter("priceBox");

        request.setAttribute("errorMsg", "fdfdf");

        try 
        {
            if (action.equals("Add")) 
            {
                invServ.insert(email, categ, item, Double.valueOf(price));
            } 
            else if(action.equals("Save"))
            {
                String edit = request.getParameter("edit");
                invServ.update(email, categ, Integer.valueOf(edit), item, Double.valueOf(price));
            }

            else if (action.equals("Delete")) 
            {
                String del = request.getParameter("delete");
                invServ.delete(del, email);
            }
        } catch (Exception e) {
            request.setAttribute("errorMsg", e);
        }

        response.sendRedirect("inventory");

    }

}
