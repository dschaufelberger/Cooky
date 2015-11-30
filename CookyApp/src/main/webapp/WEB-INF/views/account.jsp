<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 27.11.2015
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>6
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/jquery/jquery.validate.js" />"></script>
    <title>Account Data</title>
    <script>
        jQuery('.validatedForm').validate({
            rules : {
                password : {
                    minlength : 5
                },
                password_confirm : {
                    minlength : 5,
                    equalTo : "#password"
                }
            }
        });

    </script>
</head>
<body>
<!-- navbar -->
<div>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Cooky</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/user">Userlist</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="glyphicon glyphicon-user" href="/user"> Cooky-User</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
</div>
<!-- end of navbar -->

    <div class="container">
        <section style="padding-bottom: 50px; padding-top: 50px;">
            <div class="row">
                <form:form method="POST" action="/editUserData" commandName="user">
                <div class="col-md-4">
                    <img src="resources/images/profilepicture.png" class="img-rounded img-responsive" />
                    <br />
                    <br />
                    <%--<label>Username</label>
                    <input type="text" class="form-control" value="${user.username}" disabled="disabled">--%>
                    <form:label path="forename">Forename</form:label>
                    <form:input path="forename" class="form-control" />
                    <form:label path="surname">Surname</form:label>
                    <form:input path="surname" class="form-control" />
                    <form:label path="email">Email</form:label>
                    <form:input path="email" type="email" class="form-control" />
                    <label>Gender</label>
                    <%--<input type="text" class="form-control" value="${user.gender}" disabled="disabled">
                    <label>Birthdate</label>
                    <input type="text" class="form-control" value="${user.birthdate}" disabled="disabled">
                    <label>Registration Date</label>
                    <input type="text" class="form-control" value="${user.registrationDate}" disabled="disabled">--%>
                    <br>
                    <!--<a href="#" class="btn btn-success">Update Details</a>-->
                    <form:hidden path="id"/>
                    <!--<input type="hidden" name="id" value="${user.id}">-->
                    <input type="submit" class="btn btn-success" value="Update Account">

                    <br /><br />
                </div>
                </form:form>
                <div class="col-md-8">
                    <div class="alert alert-info">
                        <h2>Account settings</h2>
                        <%--<h4>${user.username}</h4>--%>
                        <p>
                            As a registered Cooky User you are able to edit your profile data and settings on this page.
                        </p>
                    </div>
                    <form class= "validatedForm" id="validatedForm" method="post" action="/changePassword">
                    <div class="form-group col-md-8">
                        <h3>Change Your Password</h3>
                        <br />
                        <label>Enter Old Password</label>
                        <input name="oldpass" type="password" class="form-control">
                        <label>Enter New Password</label>
                        <input name="password" type="password" class="form-control">
                        <label>Confirm New Password</label>
                        <input name="password_confirm" type="password" class="form-control" />
                        <br>
                        <input type="hidden" name="id" value="${password.id}">
                        <input type="submit" class="btn btn-warning" value="Change Password">

                    </div>
                    </form>
                </div>
            </div>
            <!-- ROW END -->


        </section>
        <!-- SECTION END -->
    </div>
    <!-- CONATINER END -->


</body>
</html>
