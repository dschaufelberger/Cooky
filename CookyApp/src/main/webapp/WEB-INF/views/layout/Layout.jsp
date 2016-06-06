<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 03.12.2015
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf_token" content="${_csrf.token}">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <link href="<spring:url value="/resources/css/jQueryUI/jquery-ui.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/jQueryUI/jquery-ui.structure.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/jQueryUI/jquery-ui.theme.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <link href="<spring:url value="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"/>"
          rel="stylesheet" type="text/css">
    <link href="<spring:url value="http://fonts.googleapis.com/css?family=Montserrat:400,700"/>" rel="stylesheet"
          type="text/css">
    <!-- Grayscale Theme CSS -->
    <link href="<spring:url value="/resources/css/cooky/grayscale.css" />" rel="stylesheet">
    <!-- Font Awesome fonts -->
    <link href="<spring:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/cooky/cooky-general.css"/>" rel="stylesheet">

    <script src="<spring:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<spring:url value="/resources/js/jQueryUI/jquery-ui.min.js" />"></script>
    <script src="<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <!-- Plugin JavaScript -->
    <script src="<spring:url value="/resources/js/jquery/jquery.easing.min.js" />"></script>
    <!-- Grayscale Them JavaScript -->
    <script src="<spring:url value="/resources/js/grayscale/grayscale.js" />"></script>
    <script src="<spring:url value="/resources/js/cooky/friendship.js" />"></script>

    <title><tiles:insertAttribute name="title" ignore="true" defaultValue="Cooky" /></title>
</head>
<body>

<tiles:insertAttribute name="navigation" />
<tiles:insertAttribute name="content" />

<%--<div>
    <tiles:insertAttribute name="footer" />
</div>--%>

</body>
</html>
