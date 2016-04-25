<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 28.11.2015
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registrierung erfolgreich!</title>
</head>
<body>
<c:set var="loginUrl" value="/signin"/>

<p>Willkommen bei Cooky! Ihre Registrierung war erfolgreich. Sie können sich nun anmelden und Cooky in vollem Umfang erkunden!</p>
<p>Hier geht es zur <a href="${loginUrl}">Anmeldeseite</a>.</p>
<span></span>
</body>
</html>
