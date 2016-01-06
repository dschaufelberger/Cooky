<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 29.11.2015
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
Es scheint als hätten Sie falsche Anmeldedaten benutzen. Bitte versuchen Sie es erneut mit den korrekten Anmeldedaten.
<c:set var="loginUrl" value="/loginPage"/>
<a href="${loginUrl}">Zur Anmeldeseite</a>