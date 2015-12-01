<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" action="j_spring_security_check" commandName="userCredentials">
    <table>
        <tbody>
        <tr>
            <td><form:label path="username">Benutzername</form:label></td>
            <td><form:input path="username" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Passwort</form:label></td>
            <td><form:password path="password" /></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td><input type="submit" value="Login"></td>
        </tr>
        </tfoot>
    </table>
</form:form>

<%--
<form action="/j_spring_security_check" method="post">
    <table>
        <tbody>
        <tr>
            <td><label for="username">Benutzername:</label> </td>
            <td><input type="text" name="username" id="username"></td>
        </tr>
        <tr>
            <td><label for="password">Passwort::</label> </td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td><input type="submit" value="Login"></td>
        </tr>
        </tfoot>
    </table>
</form>
--%>