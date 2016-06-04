<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 01.06.2016
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form:form method="POST" action="cookingMatches/addPreferences" commandName="categories">
    <c:forEach var="category" items="${categories.categories}">
        <form:checkbox path="userCategories" value="${category.categoryName}" />${category.categoryName} </br>
    </c:forEach>
    <input type="submit" value="Send" class="btn btn-default">
</form:form>
