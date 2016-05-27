<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 21.05.2016
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<spring:url value="/resources/js/recipeJS/recipes.js" />"></script>

<form:form method="POST" action="/recipes/recipeSuggestions" commandName="ingredientSuggestion">
    <form:checkbox
            path="recipesContainingAtLeastOneIngredient"/>Get Recipes which contain at least one of the inserted Ingredients
    <div class="form-group ingredientSuggestions">
        <form:input path="ingredients[0]" cssClass="form-control ingredients" onchange="addIngredientRow()"/>
    </div>
        <span class="input-group-btn">
        <button type="submit" class="btn btn-default btn-sm">
            <span class="glyphicon glyphicon-arrow-right"></span>
        </button>
    </span>
</form:form>
