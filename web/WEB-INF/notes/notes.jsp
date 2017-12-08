<%-- 
    Document   : notes
    Created on : Nov 1, 2017, 2:04:35 PM
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
        <title>User Page</title>
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
        <c:if test="${userSession != null}">
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
                            <a class="navbar-brand" href="notes">User</a>
                        </div>

                        <div class="collapse navbar-collapse" id="navbar-collapse-2">
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="notes">Notes Page</a></li>
                                <li><a href="view?action=account">View Account</a></li>
                                <li>
                                    <a href="notes?action=logout" class="btn btn-default btn-outline btn-circle collapsed">Logout</a>
                                </li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container -->
                </nav><!-- /.navbar -->
            </div>
        </c:if>

        <c:if test="${adminSession != null}">
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
                            <a class="navbar-brand" href="admin">System Admin</a>
                        </div>

                        <div class="collapse navbar-collapse" id="navbar-collapse-2">
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="admin">Admin Page</a></li>
                                <li><a href="notes?action=${loggedInAdmin}">Notes Page</a></li>
                                <li><a href="manageCompany">Company Page</a></li>
                                <li><a href="manageRole">Manage Role</a></li>
                                <li>
                                    <a href="admin?action=logout" class="btn btn-default btn-outline btn-circle collapsed">Logout</a>
                                </li>
                            </ul>
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container -->
                </nav><!-- /.navbar -->
            </div>
        </c:if>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4>Notes List</h4>
                    <div class="table-responsive">
                        <table id="mytable" class="table table-bordred table-striped">
                            <thead>
                            <th>Content</th>
                            <th>Title</th>
                            <th>Date Created</th>
                            <th></th>
                            <th></th>
                            </thead>
                            <c:forEach var="item" items="${notes}">
                                <tbody>
                                    <tr>
                                        <td><c:out value="${item.contents}"/></td>
                                        <td><c:out value="${item.title}"/></td>
                                        <td><c:out value="${item.dateCreated}"/></td>
                                        <td>
                                            <form action="notes" method="post" >
                                                <input type="submit" value="Delete">
                                                <input type="hidden" name="action" value="delete"><span class="glyphicon glyphicon-trash"></span>
                                                <input type="hidden" name="selectedNote" value="${item.noteID}">
                                            </form>
                                        </td>
                                        <td>
                                            <form action="notes" method="get">
                                                <input type="submit" value="Edit"><span class="glyphicon glyphicon-pencil"></span>
                                                <input type="hidden" name="action" value="view">
                                                <input type="hidden" name="selectedNoteId" value="${item.noteID}">
                                            </form>
                                        </td>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <form action="notes" method="post" class="form-horizontal" role="form">
                        <fieldset>

                            <!-- Form Name -->
                            <legend>Add Notes</legend>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="textinput">Title</label>
                                <div class="col-sm-10">
                                    <input type="text" name="title" class="form-control">
                                </div>
                            </div>

                            <!-- Text input-->
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="textinput">Contents</label>
                                <div class="col-sm-10">
                                    <input type="text" name="contents" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="pull-right">
                                        <input type="hidden" name="action" value="add">
                                        <input type="submit" value="Add Note" class="btn btn-primary">
                                    </div>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div><!-- /.col-lg-12 -->
                <c:if test="${selectedNote != null}">
                    <div class="col-md-4">
                        <form action="notes" method="post" class="form-horizontal" role="form">
                            <fieldset>

                                <!-- Form Name -->
                                <legend>Edit Notes</legend>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="textinput">Note ID</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="noteID" value="${selectedNote.noteID}" readonly class="form-control">
                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="textinput">Title</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="title" value="${selectedNote.title}" class="form-control">
                                    </div>
                                </div>

                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label" for="textinput">Contents</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="contents" value="${selectedNote.contents}" class="form-control">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <div class="pull-right">
                                            <input type="hidden" name="action" value="edit">
                                            <input type="submit" value="Save" class="btn btn-primary">
                                        </div>
                                    </div>
                                </div>

                            </fieldset>
                        </form>
                    </div><!-- /.col-lg-12 -->
                </c:if>
            </div>
        </div>
    </body>
</html>
