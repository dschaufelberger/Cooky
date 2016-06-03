<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 27.11.2015
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
<div class="row">
    <div class="col-md-12">
        <a href="/recipes/add" class="btn btn-primary btn-block">Add Recipe</a>
    </div>
</div>
</sec:authorize>

<c:forEach var="recipes" items="${recipesList}" varStatus="loop">
    <c:if test="${loop.index % 3 == 0}">
        <div class="row">
    </c:if>

    <div class="col-md-4">
        <div class="thumbnail">
            <img src="${recipes.imageLink}" alt="image">
            <div class="caption">
                <h3>${recipes.name}</h3>

                <p align="center">
                    <a href="/recipes/view/${recipes.id}" class="btn btn-primary btn-block">Open</a>
                </p>

                <form id="removeRecipe" action="/recipes/remove" method="post">
                    <p align="center">
                        <button type="submit" class="btn btn-primary btn-block">Remove</button>
                    </p>
                    <input type="hidden" name="id" value="${recipes.id}">
                    <sec:csrfInput />
                </form>
            </div>
        </div>
    </div>

    <c:if test="${loop.index % 3 == 2}">
        </div>
    </c:if>
</c:forEach>
