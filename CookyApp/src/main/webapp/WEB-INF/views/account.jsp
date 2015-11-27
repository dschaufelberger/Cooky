<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 27.11.2015
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

    <title>Ingredients</title>
</head>
<body>
<div class="container">
    <div class="col-md-6">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Field</th>
                <th>Data</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Username</td>
                <td> ${user.username} </td>
            </tr>
            <tr>
                <td>Forename</td>
                <td>${user.forename}</td>
            </tr>
            <tr>
                <td>Surname</td>
                <td>${user.surname}</td>
            </tr>
            <tr>
                <td>Email</td>
                <td>${user.email}</td>
            </tr>

            </tbody>
        </table>
    </div>
</div>


</body>
</html>
