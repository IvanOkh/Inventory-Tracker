
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Register New Inventory Tracker Account</h1>
        
        <c:if test="${control_R eq 'do_register'}">  
            <form method="post" action="register">
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
        <c:if test="${control_R eq 'cant_register'}">
            <h3> Please, log out first before registering a new account </h3>
            <br>
            <ul> 
                <li><a href ="inventory">Return to previous page</li>
                <li><a href ="account">My Account</li>
                <li><a href ="login?logout">Log out<a></li>
            </ul> 
         </c:if>  
         ${errorMsg}
    </body>
</html>
