<%-- 
    Document   : successReg
    Created on : Nov 27, 2017, 9:06:53 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value='styles/style.css'/>" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Success</title>
        <style>
            body{
                background-color: #333333;
            }
            
            .row{
                margin:auto;
            }
            .alert-message
            {
                margin: 20px 0;
                padding: 20px;
                border-left: 3px solid #eee;
            }
            .alert-message h4
            {
                margin-top: 0;
                margin-bottom: 5px;
            }
            .alert-message p:last-child
            {
                margin-bottom: 0;
            }
            .alert-message code
            {
                background-color: #fff;
                border-radius: 3px;
            }
            .alert-message-success
            {
                background-color: #F4FDF0;
                border-color: #3C763D;
            }
            .alert-message-success h4
            {
                color: #3C763D;
            }
            .alert-message-danger
            {
                background-color: #fdf7f7;
                border-color: #d9534f;
            }
            .alert-message-danger h4
            {
                color: #d9534f;
            }
            .alert-message-warning
            {
                background-color: #fcf8f2;
                border-color: #f0ad4e;
            }
            .alert-message-warning h4
            {
                color: #f0ad4e;
            }
            .alert-message-info
            {
                background-color: #f4f8fa;
                border-color: #5bc0de;
            }
            .alert-message-info h4
            {
                color: #5bc0de;
            }
            .alert-message-default
            {
                background-color: #EEE;
                border-color: #B4B4B4;
            }
            .alert-message-default h4
            {
                color: #000;
            }
            .alert-message-notice
            {
                background-color: #FCFCDD;
                border-color: #BDBD89;
            }
            .alert-message-notice h4
            {
                color: #444;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-6">
                    <div class="alert-message alert-message-success">
                        <h4>
                            Successfully Registered</h4>
                        <p>
                            Welcome ${username} Thankyou for registering!<strong> click here to login</strong>. <a href="login">
                                Login</a></p>
                    </div>
                </div>
            </div>
    </body>
</html>
