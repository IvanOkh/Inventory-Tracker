
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Tracker</title>
    </head>
<body>
        <h1>Inventory Tracker</h1>
        <h2>Welcome to your personal inventory ${currentlyUser}</h2>        
        <h3>Menu</h3>
        
        <c:if test="${isUser == true}">          
            <ul> 
                <li><a href ="account">My Account</li>
                <li><a href ="login?logout">Log out<a></li>
            </ul>      
        </c:if>
        
        <c:if test="${isUser == false}">    
            <ul> 
                <li><a href ="admin">Admin</li>
                <li><a href ="category">Manage Categories</li>
                <li><a href ="account">My Account</li>
                <li><a href ="login?logout">Log out<a></li>
            </ul>  
        </c:if>
        

        <br>  
        <h3>Inventory for ${currentlyUser}:</h3>

<!-- table -->     
        <table style="width:35%" border ="1">
          
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
            </thead>
            
            <c:forEach items ="${displayItems}" var="item">
                <tr> 
                    <td align ="center">${item.category.categoryName}</td>
                    <td align ="center">${item.itemName}</td>
                    <td align ="center">${item.price}</td>
                    <td align="center">
                        <form>
                            <input type="submit" value="Edit">
                            <input type="hidden" name="edit" value="${item.itemID}">
                        </form>
                    </td>
                    <td align="center">
                        <form method="post" action="inventory">
                            <input type="submit" value="Delete">
                            <input type="hidden" name="option" value="Delete">
                            <input type="hidden" name="delete" value="${item.itemID}">
                        </form>
                    </td>

                </tr>
            </c:forEach>
                
        </table>

<!-- add form --> 
<c:if test="${control eq 'Add'}">
            <h2>Add Item</h2>
            <form method="post" action="inventory">
                
                <a>Category:</a>
                <select name="drop">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.categoryName}">${category.categoryName}</option>
                    </c:forEach>
                </select>
                
                <br>
                <a>Name:</a>
                <input type="text" placeholder="" name="itemBox" value="${item.itemName}" required>
                <br>
                <a>Price:</a>
                <input type="text" placeholder="" name="priceBox" value="${item.price}" required>
                <br>                
                <input type="submit" value="Add">
                <input type="hidden" name="option" value="Add">
            </form>
</c:if>
            
<c:if test="${control eq 'Edit'}">
            <h2>Edit Item</h2>
            <form method="post" action="inventory">
                
                <a>Category:</a>
                <select name="drop">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.categoryName}">${category.categoryName}</option>
                    </c:forEach>
                </select>
                
                <br>
                <a>Name:</a>
                <input type="text" placeholder="" name="itemBox" value="${item.itemName}" required>
                <br>
                <a>Price:</a>
                <input type="text" placeholder="" name="priceBox" value="${item.price}" required>
                <br>                
                <input type="submit" value="Save">
                <input type="hidden" name="option" value="Save">
                <input type="hidden" name="edit" value="${item.itemID}">
            </form>
</c:if>
            
<!-- error message -->          
        <c:if test="${errorMsg ne null}">
            ${errorMsg}
        </c:if> 
    </body>  
</html>
