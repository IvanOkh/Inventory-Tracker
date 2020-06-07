
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Inventory Tracker</title>
    </head>
    
    <body>

        <h1>Welcome to Inventory Tracker</h1>
        <h2>Login</h2>
        
        <ul> 
            <li><a href ="register">Create New Account<a></li>
        </ul> 
        
        <form action="login" method="post">
            <h4> Email: <input type="text" size="30" name="inputOne"></textarea> </h4>        
            <h4> Password: <input type="text" size="25" name="inputTwo"></textarea> </h4>
            <input type="submit" value="Log in">
        </form>
        <br>
        ${inputException}

    </body>
</html>
