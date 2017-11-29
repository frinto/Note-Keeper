<%-- 
    Document   : registration
    Created on : Nov 27, 2017, 8:35:12 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Register</h1>
        
        <form action="registration?action=register" method="post">
            Username: <input type="text" name="username" placeholder="username" value="" required="required"><br>
            Password: <input type="password" name="password" placeholder="password" value="" required="required"><br>
            Email: <input type="email" name="email" placeholder="email" value="" required="required"><br>
            First Name: <input type="text" name="firstName" placeholder="first name" value="" required="required"><br>
            Last Name: <input type="text" name="lastName" placeholder="last name" value="" required="required"><br>
            Company ID: <input type="number" name="company" placeholder="company" min="1" max="3" value="" required="required"><br>
            <input type="submit" name="register" value="register">
        </form><br>
        
        ${errorMessage}
        
    </body>
</html>
