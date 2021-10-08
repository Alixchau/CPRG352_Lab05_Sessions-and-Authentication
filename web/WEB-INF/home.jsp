<%-- 
    Document   : home
    Created on : 6-Oct-2021, 10:28:53 PM
    Author     : Alix
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h2>Home Page</h2>
        <h3>Hello ${userObject.username}</h3>
        <a href="login?logout">Log out</a>
    </body>
</html>
