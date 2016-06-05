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

<form:form method="POST" action="/matchCenter/addPreferences" commandName="categories">
    <div class="cooky-table">
        <ul class="col-md-6">
            <h2>Local Cuisine</h2>
            <li><form:checkbox path="userCategories" value="European"/> European</li>
            <ul>
                <c:forEach var="category"
                           items="${categories.categories}">
                    <c:if test="${category.superCategory.equals('European')}">
                        <li><form:checkbox path="userCategories" value="${category.name}"/> ${category.name} </li>
                    </c:if>
                </c:forEach>
            </ul>
            <li><form:checkbox path="userCategories" value="Asian"/>Asian</li>
            <ul>
                <c:forEach var="category"
                           items="${categories.categories}">
                    <c:if test="${category.superCategory.equals('Asian')}">
                        <li><form:checkbox path="userCategories" value="${category.name}"/>${category.name}</li>
                    </c:if>
                </c:forEach>
            </ul>
        </ul>

        <ul class="col-md-6">
            <h2>Seasonal</h2>
            <ul>
                <c:forEach var="category"
                           items="${categories.categories}">
                    <c:if test="${category.superCategory.equals('Seasonal')}">
                        <li><form:checkbox path="userCategories" value="${category.name}"/> ${category.name} </li>
                    </c:if>
                </c:forEach>
            </ul>
        </ul>
        <ul class="col-md-6">
            <h2>Dishes</h2>
            <ul>
                <c:forEach var="category"
                           items="${categories.categories}">
                    <c:if test="${category.superCategory.equals('Dishes')}">
                        <li><form:checkbox path="userCategories" value="${category.name}"/> ${category.name} </li>
                    </c:if>
                </c:forEach>
            </ul>
        </ul>
    </div>
    <input type="submit" value="Send" class="btn btn-default">
</form:form>
