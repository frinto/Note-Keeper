<%-- 
    Document   : users
    Created on : Nov 1, 2017, 2:02:39 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='styles/style.css'/>" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Company Admin Page</title>
        <style>
            .navbar-brand { position: relative; z-index: 2; }

            .navbar-nav.navbar-right .btn { position: relative; z-index: 2; padding: 4px 20px; margin: 10px auto; transition: transform 0.3s; }

            .navbar .navbar-collapse { position: relative; overflow: hidden !important; }
            .navbar .navbar-collapse .navbar-right > li:last-child { padding-left: 22px; }

            .navbar .nav-collapse { position: absolute; z-index: 1; top: 0; left: 0; right: 0; bottom: 0; margin: 0; padding-right: 120px; padding-left: 80px; width: 100%; }
            .navbar.navbar-default .nav-collapse { background-color: #f8f8f8; }
            .navbar.navbar-inverse .nav-collapse { background-color: #222; }
            .navbar .nav-collapse .navbar-form { border-width: 0; box-shadow: none; }
            .nav-collapse>li { float: right; }

            .btn.btn-circle { border-radius: 50px; }
            .btn.btn-outline { background-color: transparent; }

            .navbar-nav.navbar-right .btn:not(.collapsed) {
                background-color: rgb(111, 84, 153);
                border-color: rgb(111, 84, 153);
                color: rgb(255, 255, 255);
            }

            .navbar.navbar-default .nav-collapse,
            .navbar.navbar-inverse .nav-collapse {
                height: auto !important;
                transition: transform 0.3s;
                transform: translate(0px,-50px);
            }
            .navbar.navbar-default .nav-collapse.in,
            .navbar.navbar-inverse .nav-collapse.in {
                transform: translate(0px,0px);
            }


            @media screen and (max-width: 767px) {
                .navbar .navbar-collapse .navbar-right > li:last-child { padding-left: 15px; padding-right: 15px; } 

                .navbar .nav-collapse { margin: 7.5px auto; padding: 0; }
                .navbar .nav-collapse .navbar-form { margin: 0; }
                .nav-collapse>li { float: none; }

                .navbar.navbar-default .nav-collapse,
                .navbar.navbar-inverse .nav-collapse {
                    transform: translate(-100%,0px);
                }
                .navbar.navbar-default .nav-collapse.in,
                .navbar.navbar-inverse .nav-collapse.in {
                    transform: translate(0px,0px);
                }

                .navbar.navbar-default .nav-collapse.slide-down,
                .navbar.navbar-inverse .nav-collapse.slide-down {
                    transform: translate(0px,-100%);
                }
                .navbar.navbar-default .nav-collapse.in.slide-down,
                .navbar.navbar-inverse .nav-collapse.in.slide-down {
                    transform: translate(0px,0px);
                }
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <nav class="navbar navbar-default">
                <div class="container">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-2">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="companyAdmin">Company Admin</a>
                    </div>

                    <div class="collapse navbar-collapse" id="navbar-collapse-2">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="companyAdmin">Company Admin Page</a></li>
                            <li>
                                <a href="admin?action=logout" class="btn btn-default btn-outline btn-circle collapsed">Logout</a>
                            </li>
                        </ul>
                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container -->
            </nav><!-- /.navbar -->
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4>User List</h4>
                    <div class="table-responsive">
                        <table id="mytable" class="table table-bordred table-striped">
                            <thead>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Email</th>
                            <th>Active</th>
                            <th>Firstname</th>
                            <th>Lastname</th>
                            <th>Company Name</th>
                            <th></th>
                            <th></th>
                            </thead>
                            <c:forEach var="item" items="${users}">
                                <tbody>
                                    <tr>
                                        <td><c:out value="${item.username}"/></td>
                                        <td><c:out value="${item.password}"/></td>
                                        <td><c:out value="${item.email}"/></td>
                                        <td><c:out value="${item.active}"/></td>
                                        <td><c:out value="${item.firstname}"/></td>
                                        <td><c:out value="${item.lastname}"/></td>
                                        <td><c:out value="${item.company.companyName}"/></td>
                                        <td>
                                            <form action="companyAdmin" method="post" >
                                                <input type="submit" value="Delete">
                                                <input type="hidden" name="action" value="delete"><span class="glyphicon glyphicon-trash"></span>
                                                <input type="hidden" name="selectedUser" value="${item.username}">
                                            </form>
                                        </td>
                                        <td>
                                            <form action="companyAdmin" method="get">
                                                <input type="submit" value="Edit"><span class="glyphicon glyphicon-pencil"></span>
                                                <input type="hidden" name="action" value="view">
                                                <input type="hidden" name="selectedUser" value="${item.username}">
                                            </form>
                                        </td>
                                    </tr>  
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            ${errorDelete}

            <c:if test="${selectedUser != null}">
                <h4>Edit User</h4>
                <form action="companyAdmin" method="POST">
                    Username: <input type="text" name="username" value="${selectedUser.username}" readonly><br>
                    Password: <input type="password" name="password" value="${selectedUser.password}"><br>
                    Email:	  <input type="text" name="email" value="${selectedUser.email}"><br>
                    Active:	  <input type="number" name="active" min="0" max="1" value="${selectedUserActive}"><br>
                    First name:<input type="text" name="firstname" value="${selectedUser.firstname}"><br>
                    Last name: <input type="text" name="lastname" value="${selectedUser.lastname}"><br>
                    Role: 	  <input type="number" name="role" min="1" max="2" value="${selectedUserRole}"><br>
                    Company:  <input type="number" name="company" min="1" max="3" value="${selectedUserCompany}"><br>
                    <input type="hidden" name="action" value="edit">
                    <input type="submit" value="Save">
                </form>
            </c:if>

            <h4>Add Users</h4>
            <form action="companyAdmin" method="POST">
                Username: <input type="text" name="username" value=""><br>
                Password: <input type="password" name="password" value=""><br>
                Email:	  <input type="text" name="email" value=""><br>
                Active:	  <input type="number" name="active" min="0" max="1" value=""><br>
                First name:<input type="text" name="firstname" value=""><br>
                Last name: <input type="text" name="lastname" value=""><br>
                Role: 	  <input type="number" name="role" min="1" max="2" value=""><br>
                Company: 	  <input type="number" name="company" min="1" max="3" value=""><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add User">
            </form><br>
            ${errorMessage}
        </div><br>
    </body>
</html>
