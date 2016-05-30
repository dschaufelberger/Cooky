<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 07.05.2016
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<spring:url value="/resources/js/recipeJS/recipes.js" />"></script>
<jsp:useBean id="search" class="de.cookyapp.web.viewmodel.Search" scope="request" />

<div class="input-group">
    <form:input path="searchQuery" id="searchPlaceholder" cssClass="form-control input-sm"  placeholder="Search for recipes..." />
    <sec:authorize access="isAuthenticated()">
    <span class="input-group-addon" style="padding: 0px"><form:select path="searchType" id="searchTypeDropdown" onchange="changeSearchPlaceholder()" cssClass="btn-sm" items="${availableSearchTypes}"/></span>
    </sec:authorize>
    <span class="input-group-btn">
        <button type="submit" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-search"></span>
        </button>
    </span>
</div>