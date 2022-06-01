<%-- 
    Document   : user
    Created on : May 28, 2022, 4:04:50 PM
    Author     : ROG STRIX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <h1>Hello</h1>
        <form action="MainController" method="POST">
            <input type="hidden" name="search" value=" ">
            <input type="hidden" name="address" value=" ">
            <button type="submit" value="SearchHistory" name="action">Go to history</button>
        </form>
        <form action="MainController">  
            <input type="submit" name="action" value="Logout"/>
        </form>
        <a href="resetPassword.jsp">Change your password here</a>
    </body>
</html>
