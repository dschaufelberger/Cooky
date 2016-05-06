<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 02.05.2016
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:eval expression="cookbook.visibility == T(de.cookyapp.enums.CookbookVisibility).PUBLIC" var="isPublic" />
<spring:eval expression="cookbook.visibility == T(de.cookyapp.enums.CookbookVisibility).PRIVATE" var="isPrivate" />
<spring:eval expression="cookbook.visibility == T(de.cookyapp.enums.CookbookVisibility).FRIENDS" var="isShared" />

<h1>${cookbook.name}</h1>
<div class="panel panel-default">
    <div class="panel-body">
        <p>${cookbook.shortDescription}</p>
    </div>
</div>

<c:if test="${isPublic}">
    <span>This cookbook is visible to all Cookys.</span>
</c:if>
<c:if test="${isPrivate}">
    <span>This cookbook is only visibile to you.</span>
</c:if>
<c:if test="${isShared}">
    <span>This cookbook is visible to all your Cooky Friends.</span>
</c:if>

<h4>Recipes in this cookbook</h4>
<div class="list-group">
    <c:forEach var="recipe" items="${cookbook.recipes}">
        <div class="list-group-item">
            <a href="/recipes/goToEditRecipe/${recipe.id}">
                <h4 class="list-group-item-heading">${recipe.name}</h4>
            </a>
            <span>
                <c:forEach begin="1" end="${recipe.rating}">
                    <span class="glyphicon glyphicon-star cooky-recipeRating"></span>
                </c:forEach>
                <c:forEach begin="${recipe.rating + 1}" end="${recipe.maxRating}">
                    <span class="glyphicon glyphicon-star-empty"></span>
                </c:forEach>
            </span>
            <p>${recipe.description}</p>
        </div>

    </c:forEach>
</div>
