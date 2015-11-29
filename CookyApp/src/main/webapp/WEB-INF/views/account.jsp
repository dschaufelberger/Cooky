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
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

    <title>Account Data</title>
</head>
<body>
<form:form method="POST" action="/editUserData" commandName="user">
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
                <td>ID</td>
                <td> ${user.id} </td>
            </tr>
            <tr>
                <td>Username</td>
                <td> ${user.username} </td>
            </tr>
            <tr>
                <td><form:label path="forename">Forename</form:label></td>
                <td><form:input path="forename"/></td>
            </tr>
            <tr>
                <td><form:label path="surname">Surname</form:label></td>
                <td><form:input path="surname"/></td>
            </tr>
            <tr>
                <td><form:label path="email">Email</form:label></td>
                <td><form:input path="email" type="email" /></td>
            </tr>
            <tr>
                <td><form:label path="password">Password</form:label></td>
                <td><form:input path="password" type="password"/></td>
            </tr>
            <tr>
                <td>Gender</td>
                <td> ${user.gender} </td>
            </tr>
            <tr>
                <td>Birthdate</td>
                <td> ${user.birthdate} </td>
            </tr>
            <tr>
                <td>Registration Date</td>
                <td> ${user.registrationDate} </td>
            </tr>
            <tr>
                <td>Account State</td>
                <td> ${user.accountState} </td>
            </tr>

            </tbody>
            <tfoot>
            <tr class="formSubmitRow">
                <td><input type="submit" value="Speichern"></td>
                <input type="hidden" name="id" value="${user.id}">
            </tr>
            </tfoot>
        </table>
    </div>
</div>

</form:form>
</body>
</html>
