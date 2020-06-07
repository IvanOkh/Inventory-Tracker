
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Account</title>
    </head>
    <body>
        <h1>Inventory Tracker - User Account</h1>
        <h2>Welcome to your personal account  ${user.fname}</h2>
        <h3>Menu</h3>
        
         <c:if test="${isUser == true}">          
            <ul> 
                <li><a href ="inventory">My Inventory</li>
                <li><a href ="login?logout">Log out<a></li>
            </ul>      
        </c:if>
        
        <c:if test="${isUser == false}">    
            <ul> 
                <li><a href ="admin">Admin</li>
                <li><a href ="category">Manage Categories</li>
                <li><a href ="inventory">My Inventory</li>
                <li><a href ="login?logout">Log out<a></li>
            </ul>  
        </c:if>
        
        <table style="width:50%" border="1">
        <thead>
                <tr>
                    <th>Active User</th>
                    <th>Login Name</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Deactivate Account</th>
                    <th>Edit</th>
                </tr>
        </thead>            
            <tr>
                <td>${user.active}</td>
                <td>${user.email}</td>
                <td>${user.fname}</td>
                <td>${user.lname}</td>                   
                <td align="center">
                    <form>
                        <input type="submit" value="Deactivate"> 
                        <input type="hidden" name="delete" value="${user.email}">
                    </form>
                </td>
                <td align ="center">
                    <form>
                        <input type="submit" value="Edit"> 
                        <input type="hidden" name="edit" value="${user.email}">
                    </form>
                </td>
            </tr>
        </table>

<!-- delete form -->                
        <c:if test="${control eq 'Delete'}">
            <h4><b>Are you sure you want to deactivate your user account ${user.email}?</b> </h4>
            <h5>This will log you out and only a system administrator will be able to restore your account.</h5>

            <form method="post" action="account">
                <input type="submit" value="Deactivate My Account">
                <input type="hidden" name="option" value="Delete">
                <input type="hidden" name="delete" value="${user.email}">
            </form>
        </c:if>
<!-- edit form -->          
        <c:if test="${control eq 'Edit'}"> 
            <h2>Edit Your Account</h2>
            
            <form method="post" action="account">
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
