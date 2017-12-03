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
        <link rel="stylesheet" href="<c:url value='styles/style.css'/>" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Registration</title>
        <style>
            @import url(http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700);
            @import url(http://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,700);

            body {
                background-color:#e6e6e6;
                font-family: 'Roboto', sans-serif;
                line-height: 22px;	
            }
            h1, h2, h3, h4, h5, h6 {
                font-family: 'Roboto Condensed', sans-serif;
                font-weight: 400;
                color:#111;
                margin-top:5px;
                margin-bottom:5px;
            }
            h1, h2, h3 {
                text-transform:uppercase;
            }

            input.upload {
                position: absolute;
                top: 0;
                right: 0;
                margin: 0;
                padding: 0;
                font-size: 12px;
                cursor: pointer;
                opacity: 1;
                filter: alpha(opacity=1);    
            }

            .form-inline .form-group{
                margin-left: 0;
                margin-right: 0;
            }
            .control-label {
                color:#333333;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <section>      
                        <h1 class="entry-title"><span>Register</span> </h1>
                        <hr>
                        <form class="form-horizontal" action="registration?action=register" method="post" name="signup" id="signup">        
                            <div class="form-group">
                                <label class="control-label col-sm-3">Username <span class="text-danger">*</span></label>
                                <div class="col-md-8 col-sm-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input type="text" class="form-control" name="username" id="username" placeholder="Enter your Username" value="" required="required">
                                    </div>
                                    <small>  </small> </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Email <span class="text-danger">*</span></label>
                                <div class="col-md-8 col-sm-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                        <input type="email" class="form-control" name="email" id="emailid" placeholder="Enter your Email" value="" required="required">
                                    </div>
                                    <small>  </small> </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Password <span class="text-danger">*</span></label>
                                <div class="col-md-5 col-sm-8">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input type="password" class="form-control" name="password" id="password" placeholder="Choose password (5-15 chars)" value="" required="required">
                                    </div>   
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">First Name <span class="text-danger">*</span></label>
                                <div class="col-md-8 col-sm-9">
                                    <input type="text" class="form-control" name="firstName" id="mem_name" placeholder="Enter your First Name here" value="" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Last Name <span class="text-danger">*</span></label>
                                <div class="col-md-8 col-sm-9">
                                    <input type="text" class="form-control" name="lastName" id="mem_name" placeholder="Enter your Last Name here" value="" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Company ID <span class="text-danger">*</span></label>
                                <div class="col-md-8 col-sm-9">
                                    <input type="number" class="form-control" name="company" id="mem_name" min="1" max="3" value="1" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-offset-3 col-xs-10">
                                    <input name="register" type="submit" value="register" class="btn btn-primary"><br><br>
                                    ${errorMessage}
                                </div>
                            </div>
                        </form>
                </div>
            </div>
        </div>

    </body>
</html>
