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
    <link href="<spring:url value="/resources/css/jQueryUI/jquery-ui.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/jQueryUI/jquery-ui.structure.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/jQueryUI/jquery-ui.theme.min.css" />" rel="stylesheet">
    <link href="<spring:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <%--<link href="<spring:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">--%>
    <link href="<spring:url value="/resources/css/cooky/cooky-general.css"/>" rel="stylesheet">
    <script src="<spring:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<spring:url value="/resources/js/jQueryUI/jquery-ui.min.js" />"></script>
    <script src="<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

    <title><tiles:insertAttribute name="title" ignore="true" defaultValue="Cooky" /></title>
</head>
<body class="cooky-background">

<tiles:insertAttribute name="navigation" />

<div class="container-fluid cooky-fill">
    <div class="row cooky-fill">
        <div class="col-md-2"></div>
        <div class="col-md-8 cooky-mainContent cooky-fill">
            <tiles:insertAttribute name="content" />
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
<%--<div>
    <tiles:insertAttribute name="footer" />
</div>--%>
</body>
</html>
