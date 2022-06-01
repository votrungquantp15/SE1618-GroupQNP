<%-- 
    Document   : create
    Created on : May 26, 2022, 1:16:28 PM
    Author     : ROG STRIX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            User ID<input type="text" name="userID"/></br>
            Full Name<input type="text" name="fullName"/></br>
            Password<input type="password" name="password"/></br>
            Confirm password<input type="password" name="confirm"/></br>
            NickName<input type="text" name="accName"/></br>
            Address<input type="text" name="address"/></br>           
            BirthDay<input type="date" name="birthDay" min="1995-01-01" max="2022-05-27"/></br>
            Phone<input type="number" name="phone"/></br>
            Email<input type="email" name="email"/></br>
<!--            Role<input type="text" name="roleID"/></br>
            status<input type="number" name="status" min="0" max="1"/></br>-->
            <input type="submit" name="action" value="CreateAccountForUser"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>
