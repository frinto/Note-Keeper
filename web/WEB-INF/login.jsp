<%-- 
    Document   : index
    Created on : Oct 29, 2017, 11:38:20 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        
        <form action="login?action=login" method="post">
            Username:<input type="text" name="username" value=""/><br>
            Password:<input type="password" name="password" value=""/><br>
            <input type="submit" value="Login"/>
        </form>
        ${errorMsg}
        
    </body>
</html>
