<%-- 
    Document   : manageCompany
    Created on : Nov 27, 2017, 9:31:42 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Company</title>
    </head>
    <body>
        <h1>Manage Companies</h1>
        
        hello ${loggedInAdmin}<br> 
        <a href="admin?action=logout">Logout</a>
        <a href="admin">Admin Page</a>
        <a href="notes?action=${loggedInAdmin}">Notes Page</a>
        <a href="manageCompany">Manage Company</a>
        
        
    </body>
</html>
