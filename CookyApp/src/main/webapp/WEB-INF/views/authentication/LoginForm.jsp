<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 29.11.2015
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login to Cooky</title>
</head>
<body>
<form:form method="post" action="j_spring_security_check" commandName="loginForm">
    <table>
        <tbody>
        <tr>
            <td><form:label path="j_username">Benutzername</form:label></td>
            <td><form:input path="j_username" /></td>
        </tr>
        <tr>
            <td><form:label path="j_password">Passwort</form:label></td>
            <td><form:password path="j_password" /></td>
        </tr>
        </tbody>
        <tfoot>
        <tr><td><input type="submit" value="Login"></td></tr>
        </tfoot>
    </table>
</form:form>
</body>
</html>
