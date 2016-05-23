<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 23.05.2016
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="<spring:url value="/resources/css/cooky/cooky-recipes.css"/>" rel="stylesheet">

<div>
    <p>
        <h1>${recipeOfTheMonth.name}</h1>
    </p>


</div>