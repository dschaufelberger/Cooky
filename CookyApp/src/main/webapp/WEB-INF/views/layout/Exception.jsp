<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 22.05.2016
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>

<div class="alert alert-danger" role="alert">
    <span><h3>Oops, something went wrong! Cooky could not handle your request.</h3></span>
    <span>
        <tiles:insertAttribute name="errorContent" />
    </span>
</div>


<!--
Failed URL: <c:out value="${url}" />
Current User: <c:out value="${username}" />
Exception: <c:out value="${exception.message}" />
<c:forEach items="${exception.stackTrace}" var="ste">
    <c:out value="${ste}" />
</c:forEach>
-->