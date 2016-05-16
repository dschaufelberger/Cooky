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
    <link href="<spring:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <%--<link href="<spring:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">--%>
    <link href="<spring:url value="/resources/css/cooky/cooky-general.css"/>" rel="stylesheet">
    <script src="<spring:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <!-- Custom CSS -->
    <link href="<spring:url value="/resources/css/cooky/grayscale.css" />" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<spring:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
    <!-- Plugin JavaScript -->
    <script src="<spring:url value="/resources/js/jquery/jquery.easing.min.js" />"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<spring:url value="/resources/js/grayscale/grayscale.js" />"></script>
    <link href="<spring:url value="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"/>" rel="stylesheet" type="text/css">
    <link href="<spring:url value="http://fonts.googleapis.com/css?family=Montserrat:400,700"/>" rel="stylesheet" type="text/css">

    <title><tiles:insertAttribute name="title" ignore="true" defaultValue="Cooky" /></title>
</head>
<body>

<tiles:insertAttribute name="navigation" />
<tiles:insertAttribute name="content" />

</body>
</html>
