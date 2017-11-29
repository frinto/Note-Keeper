<%-- 
    Document   : manageCompany
    Created on : Nov 27, 2017, 9:31:42 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Company</title>
    </head>
    <body>
        <h1>Manage Companies</h1>

        <a href="admin?action=logout">Logout</a>
        <a href="admin">Admin Page</a>
        <a href="notes?action=${loggedInAdmin}">Notes Page</a>
        <a href="manageCompany">Manage Company</a><br>
        hello ${loggedInAdmin}<br> 
        <table>
            <tr>
                <th>Company ID</th>
                <th>Company Name</th>
                <th></th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${companys}">

                <tr>
                    <td><c:out value="${item.companyID}"/></td>
                    <td><c:out value="${item.companyName}"/></td>

                    <td>
                        <form action="manageCompany" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedCompany" value="${item.companyID}">
                        </form>
                    </td>
                    <td>
                        <form action="manageCompany" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedCompanyID" value="${item.companyID}">
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>

        <c:if test="${selectedCompany != null}">
            <h3>Edit Company</h3>
            <form action="manageCompany" method="POST">
                Company ID: <input type="text" name="companyID" value="${selectedCompany.companyID}" readonly><br>
                Company Name: <input type="text" name="companyName" value="${selectedCompany.companyName}"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>

        <h1>Add Company</h1>
        <form action="manageCompany" method="POST">
            Company Name: <input type="text" name="companyName"><br>
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add Company">
        </form><br>
        
        ${errorMessage}

    </body>
</html>
