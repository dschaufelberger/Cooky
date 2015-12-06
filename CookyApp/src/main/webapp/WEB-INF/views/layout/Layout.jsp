<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 03.12.2015
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cooky" uri="http://cookyapp.de/tags" %>

<!DOCTYPE html>
<html class="full">
<head>
    <meta charset="UTF-8">
    <link href="<spring:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">
    <link href="<spring:url value="/resources/css/cooky/cooky-general.css"/>" rel="stylesheet">
    <script src="<spring:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

    <title><tiles:insertAttribute name="title" ignore="true" defaultValue="Cooky" /></title>
</head>
<body>
<div>
    <tiles:insertAttribute name="navigation" />
</div>
<div class="container">
    <tiles:insertAttribute name="content" />
</div>
<%--<div>
    <tiles:insertAttribute name="footer" />
</div>--%>
</body>
</html>
