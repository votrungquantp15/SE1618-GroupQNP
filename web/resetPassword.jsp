<%-- 
    Document   : resetPassword
    Created on : May 29, 2022, 1:18:19 PM
    Author     : ROG STRIX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            Input email<input type="email" name="email"/></br>
            Input current password<input type="password" name="password"></br>
            Input new password<input type="password" name="newPassword"></br>
            Confirm Password<input type="password" name="confirm"></br>
            <input type="submit" name="action" value="ResetPassword">
            <input type="reset" value="Reset">
        </form>
        
    </body>
</html>
