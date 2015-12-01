<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 27.11.2015
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

    <title>User-Overview</title>
</head>
<body>
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

<div class="col-md-6">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Username</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.username}</td>
                <form id="edit" action="account" method="post">
                    <td><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-user"></span></button></td>
                    <input type="hidden" name="id" value="${user.id}">
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
