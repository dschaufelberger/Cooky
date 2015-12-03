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

<!DOCTYPE html>
<html class="full">
<head>
    <meta charset="UTF-8">
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>

    <title><tiles:insertAttribute name="title" ignore="true" defaultValue="Cooky" /></title>
</head>
<body>
<div>
    <tiles:insertAttribute name="navigation" />
</div>
<div class="container">
    <tiles:insertAttribute name="content" />
</div>
<div>
    <tiles:insertAttribute name="footer" />
</div>
</body>
</html>

<%--<div>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">Cooky</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                &lt;%&ndash;<ul class="nav navbar-nav">
                  <li>
                    <a href="/ingredients">Ingredients</a>
                  </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                  <li>
                    <a class="glyphicon glyphicon-user" href="#"> Jasper</a>
                  </li>
                </ul>&ndash;%&gt;
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
</div>
<div class="container">
</div>--%>
