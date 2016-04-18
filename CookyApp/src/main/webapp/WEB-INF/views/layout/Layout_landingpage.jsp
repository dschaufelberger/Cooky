<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 18.04.2016
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<spring:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet">
    <%--<link href="<spring:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">--%>
    <link href="<spring:url value="/resources/css/cooky/cooky-general.css"/>" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<spring:url value="/resources/css/cooky/grayscale.css" />" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="<spring:url value="/resources/fonts/font-awesome.min.css" />" rel="stylesheet">
    <script src="<spring:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<spring:url value="/resources/js/jquery/jquery.js" />"></script>
    <script src="<spring:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
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

<div>
    <!-- Intro Header -->
    <header class="intro">
        <div class="intro-body">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 colorgray margintitle">
                        <h1 class="brand-heading">Cooky</h1>
                        <p class="intro-text">The social cooking experience.</p>
                        <a href="#about" class="glyphicon glyphicon-circle-arrow-down page-scroll colorgray"></a>
                    </div>
                </div>
            </div>
        </div>
    </header>


    <!-- About Section -->
    <section id="about" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>About Cooky</h2>
                <p>Have you ever been in the situation where your fridge was half empty and you did not know what to cook with the remaining ingredients?
                    You do not want to cook and eat alone anymore because cooking with your friends is more fun? <br><br>
                    <strong>Let Cooky handle this for you!</strong></p>
            </div>
        </div>
    </section>

    <!-- Download Section -->
    <section id="download" class="content-section text-center">
        <div class="download-section">
            <div class="container">
                <div class="col-lg-8 col-lg-offset-2 margintitle">
                    <h2>Register now to be a Cooky</h2>
                    <p>You can register with an username and your mail address.</p>
                    <a href="/registration" class="btn btn-default btn-lg">Register now!</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>Our Blog</h2>
                <p>Be informed about our newest development status.</p>
                <p><a href="https://cookysblog.wordpress.com/">https://cookysblog.wordpress.com/</a>
                </p>
                <ul class="list-inline banner-social-buttons">
                    <li>
                        <a href="https://github.com/1developer1/Cooky" class="btn btn-default btn-lg"><i
                                class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </section>
    <!-- Footer -->
    <footer>
        <div class="container text-center">
            <p>Copyright &copy; Cooky Corp 2016</p>
        </div>
    </footer>

</div>



</body>
</html>
