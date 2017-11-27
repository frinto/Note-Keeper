<%-- 
    Document   : edit
    Created on : Nov 1, 2017, 2:05:52 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
    </head>
    <body>
        <h1>Edit</h1>

        <c:if test="${userSession != null}">
            <a href="notes?action=logout">Logout</a>
                        <a href="notes">Notes Page</a>
            <a href="view?action=account">View Account</a><br><br>
        </c:if>

        <c:if test="${selectedUser != null}">
            <form action="edit" method="POST">
                Username: <input type="text" name="username" value="${selectedUser.username}" readonly><br>
                Password: <input type="password" name="password" value="${selectedUser.password}"><br>
                Email:	  <input type="text" name="email" value="${selectedUser.email}"><br>
                Active:	  <input type="number" name="active" min="0" max="1" value="${selectedUserActive}"><br>
                First name:<input type="text" name="firstname" value="${selectedUser.firstname}"><br>
                Last name: <input type="text" name="lastname" value="${selectedUser.lastname}"><br>
                Role: 	  <input type="number" name="role" min="2" max="2" value="${selectedUserRole}"readonly><br>
                Company: <input type="number" name="company" min="1" max="3" value="${selectedUserCompany}"readonly><br>
                <input type="hidden" name="action" value="editAccount">
                <input type="submit" value="Save">
            </form>
        </c:if>
            
        ${editSuccess}

    </body>
</html>
