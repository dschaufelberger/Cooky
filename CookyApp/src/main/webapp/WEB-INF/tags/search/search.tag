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

<sec:authorize access="isAuthenticated()" var="userIsAuthenticated" />
<c:if test="${userIsAuthenticated}">
    <c:set var="searchInputID" value="searchPlaceholder" />
</c:if>
<c:if test="${not userIsAuthenticated}">
    <c:set var="searchInputID" value="searchQueryInput" />
</c:if>

<div id="search-group" class="input-group search-group">
    <form:input path="searchQuery" id="${searchInputID}" cssClass="form-control input-sm"
                placeholder="Search for ..." />

    <c:if test="${userIsAuthenticated}">
        <form:select path="searchType" id="searchTypeDropdown" cssClass="form-control input-sm"
                     items="${availableSearchTypes}" itemLabel="searchType" />
    </c:if>
    <span class="input-group-btn">
        <button type="submit" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-search"></span>
        </button>
    </span>
</div>