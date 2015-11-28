<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 27.11.2015
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register on Cooky</title>
    <meta charset="UTF-8">
</head>
<body>
<form:form method="POST" action="/registration" commandName="user">
    <table>
        <tbody>
        <tr>
            <td><form:label path="username">Benutzername</form:label></td>
            <td><form:input path="username" /></td>
            <td><form:errors path="username" cssClass="formError" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Passwort</form:label></td>
            <td><form:password path="password" /></td>
            <td><form:errors path="password" cssClass="formError" /></td>
        </tr>
        <tr class="formGapRow">
        </tr>
        <tr>
            <td><form:label path="forename">Vorname:</form:label></td>
            <td><form:input path="forename" /></td>
            <td><form:errors path="forename" cssClass="formError" /></td>
        </tr>
        <tr>
            <td><form:label path="surname">Nachname:</form:label></td>
            <td><form:input path="surname" /></td>
            <td><form:errors path="surname" cssClass="formError" /></td>
        </tr>
        <tr>
            <td><form:label path="email">Email:</form:label></td>
            <td><form:input path="email" type="email" /></td>
            <td><form:errors path="email" cssClass="formError" /></td>
        </tr>
        <tr>
            <td><form:label path="gender">Anrede</form:label></td>
            <td><form:select path="gender">
                <form:option value="" label="Bitte auswählen..." />
                <form:options items="${availableGenders}" itemLabel="addressForm" />
            </form:select></td>
            <td><form:errors path="gender" cssClass="formError" /></td>
        </tr>
        <tr>
            <td><form:label path="birthdate">Geburtsdatum</form:label></td>
            <td><form:input path="birthdate" /></td>
            <td><form:errors path="birthdate" cssClass="formError" /></td>
        </tr>
        </tbody>
        <tfoot>
        <tr class="formSubmitRow">
            <td><input type="submit"></td>
        </tr>
        </tfoot>
    </table>
</form:form>
</body>
</html>
