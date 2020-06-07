
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Account</title>
    </head>
    <body>
        
        <h1>Inventory Tracker - Admin Account</h1>
        <h2>Welcome ${show}</h2>

        <h3>Menu</h3>

        <ul> 
            <li><a href ="category">Manage Categories</li>
            <li><a href ="inventory">Inventory</li>
            <li><a href ="account">My Account</li>
            <li><a href ="login?logout">Log out<a></li>
        </ul>  
        
<!-- table -->     
        <table style="width:50%" border="1">
          
            <thead>
                <tr>
                    <th>Active user</th>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Delete</th>
                    <th>Edit</th>
                    <!--<th>Re-activate Account</th>-->
                </tr>
            </thead>
            
            <c:forEach items ="${displayUsers}" var="user">
                <tr> 
                    <td align ="center">${user.active}</td>
                    <td>${user.email}</td>
                    <td align ="center">${user.fname}</td>
                    <td align ="center">${user.lname}</td>
                    <td align="center">
                        <form>
                            <input type="submit" value="Delete"> 
                            <input type="hidden" name="delete" value="${user.email}">
                        </form>
                    </td>
                    <td align ="center">
                        <form>
                            <input type="submit" value="Edit"> 
                            <input type="hidden" name="edit" value="${user.email}">
                        </form>
                    </td>
                    
                    <c:if test="${user.active == false}">
                           <td align ="center">
                                <form method="post" action="admin">
                                    <input type="submit" value="Re-activate account">
                                    <input type="hidden" name="option" value="Activate">
                                    <input type="hidden" name="activate" value="${user.email}">
                                </form>
                           </td>
                   </c:if>

                </tr>
            </c:forEach>
                
        </table>
<!-- add form -->          
        <c:if test="${control eq 'Add'}">
            <h2>Add User</h2>
            <form method="post" action="admin">
                <input type="text" placeholder="email" name="emailBox" size="30" value="${user.email}" required>
                <br>
                <input type="text" placeholder="fname" name="fnameBox" size="30" value="${user.fname}" required>
                <br>
                <input type="text" placeholder="lname" name="lnameBox" size="30" value="${user.lname}" required>
                <br>
                <input type="text" placeholder="password" name="passwordBox" size="30" value="${user.password}" required>
                <br>                
                <input type="submit" value="Save">
                <input type="hidden" name="option" value="Add">
            </form>
        </c:if>
<!-- delete form -->                
        <c:if test="${control eq 'Delete'}">
            <h4>Are you sure you want to delete user ${user.email}? </h4>

            <form method="post" action="admin">
                <input type="submit" value="Delete User">
                <input type="hidden" name="option" value="Delete">
                <input type="hidden" name="delete" value="${user.email}">
            </form>
        </c:if>
<!-- edit form -->          
        <c:if test="${control eq 'Edit'}"> 
            <h2>Edit User</h2>
            
            <form method="post" action="admin">
                <input type="text" placeholder="email" name="emailBox" size="30" value="${user.email}" disabled>
                <br>
                <input type="text" placeholder="fname" name="fnameBox" size="30" value="${user.fname}" required>
                <br>
                <input type="text" placeholder="lname" name="lnameBox" size="30" value="${user.lname}" required>
                <br>
                <input type="text" placeholder="password" name="passwordBox" size="30" value="${user.password}" required>
                <br>                
                <input type="submit" value="Save">
                <input type="hidden" name="option" value="Save">
                <input type="hidden" name="edit" value="${user.email}">
            </form>
        </c:if>
<!-- error message -->          
        <c:if test="${errorMsg ne null}">
            ${errorMsg}
        </c:if>
    </body>
</html>

