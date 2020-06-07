
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
    </head>
    <body>
        <h1>Inventory Tracker - Categories</h1>
        <h2>Welcome to category management  ${show}</h2>
        
        <h3>Menu</h3>

        <ul> 
            <li><a href ="admin">Admin</li>
            <li><a href ="inventory">Inventory</li>
            <li><a href ="account">My Account</li>
            <li><a href ="login?logout">Log out<a></li>
        </ul>  
        
        <table style="width:50%" border="1">
          
            <thead>
                <tr>
                    <th>Category Name</th>
                    <th>Edit</th>
                    <!--<th>Re-activate Account</th>-->
                </tr>
            </thead>
            
            <c:forEach items ="${displayCategories}" var="category">
                <tr> 
                    <td>${category.categoryName}</td>

                    <td align ="center">
                        <form>
                            <input type="submit" value="Edit"> 
                            <input type="hidden" name="edit" value="${category.categoryID}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
                
        </table>
        <!-- add form -->          
        <c:if test="${control eq 'Add'}">
            <h2>Add Category</h2>
            <form method="post" action="category">
                <input type="text" placeholder="New category name" name="categoryName" 
                       size="30" value="${category.categoryName}" required>
                <br>
              
                <input type="submit" value="Save">
                <input type="hidden" name="option" value="Add">
            </form>
        </c:if>
        <!-- edit form -->  
        <c:if test="${control eq 'Edit'}"> 
            <h2>Edit Category Name</h2> 
            <form method="post" action="category">
                <input type="text" placeholder="" name="categoryBox" 
                       size="30" value="${category.categoryName}" required>
                <br>
                <input type="submit" value="Save">
                <input type="hidden" name="option" value="Save">
                <input type="hidden" name="edit" value="${category.categoryID}">
            </form>
        </c:if>
    </body>
</html>
