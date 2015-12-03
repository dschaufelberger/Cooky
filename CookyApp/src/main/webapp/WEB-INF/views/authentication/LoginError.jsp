<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 29.11.2015
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Anmeldung fehlgeschlagen!</title>
</head>
<body>
Es scheint als hätten Sie falsche Anmeldedaten benutzen. Bitte versuchen Sie es erneut mit den korrekten Anmeldedaten.
<c:set var="loginUrl" value="/loginPage"/>

<a href="${loginUrl}">Zur Anmeldeseite</a>
</body>
</html>
