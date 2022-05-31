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
        <form action="MainController">
            <input type="text" name="search" readonly="Field Name">
            <select name="address">
                <option value="HCM">Hồ Chí Minh</option>
                <option value="HN">Hà Nội</option>
                <option value="DN">Đà Nẵng</option>
                <option value="NT">Nha Trang</option>
            </select>
            <input type="submit" name="action" value="Search">
        </form>

        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <a href="resetPassword.jsp">Change your password here</a>
    </body>
</html>
