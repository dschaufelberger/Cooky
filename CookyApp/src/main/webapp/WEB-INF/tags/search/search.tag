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

<jsp:useBean id="search" class="de.cookyapp.web.viewmodel.Search" scope="request" />

<div class="form-group">
    <form:input path="searchQuery" cssClass="form-control input-sm" />
    <button class="btn btn-default btn-sm" type="submit">
        <span class="glyphicon glyphicon-search"></span>
    </button>
</div>