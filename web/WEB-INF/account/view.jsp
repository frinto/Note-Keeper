<%-- 
    Document   : view
    Created on : Nov 1, 2017, 2:05:45 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Account</title>
    </head>
    <body>


        <c:if test="${userSession != null}">
            <h1>User Account</h1>

            Manage your account ${loggedInUser}<br><br>
            <a href="notes?action=logout">Logout</a>
            <a href="notes">Notes Page</a>
            <a href="view?action=account">View Account</a><br><br>
        </c:if>

        <table>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Active</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Company Name</th>
                <th></th>
            </tr>
            <tr>
                <td><c:out value="${userView.username}"/></td>
                <td><c:out value="${userView.password}"/></td>
                <td><c:out value="${userView.email}"/></td>
                <td><c:out value="${userView.active}"/></td>
                <td><c:out value="${userView.firstname}"/></td>
                <td><c:out value="${userView.lastname}"/></td>
                <td><c:out value="${userView.company.companyName}"/></td>
                <td>
                    <form action="view" method="get">
                        <input type="submit" value="Edit">
                        <input type="hidden" name="action" value="viewUser">
                        <input type="hidden" name="selectedUser" value="${userView.username}">
                    </form>
                </td>
            </tr> 
        </table>  
    </body>
</html>
