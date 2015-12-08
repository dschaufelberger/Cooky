<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 03.12.2015
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="cooky" uri="http://cookyapp.de/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/cooky/cooky-general.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

    <title>Anmelden bei Cooky</title>
</head>
<body>
<div class="container">
    <cooky:login />
</div>
</body>
</html>
