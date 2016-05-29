<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cooky" uri="http://cookyapp.de/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 27.05.2016
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1><c:out value="${recipe.name}" /></h1>

<div class="row">
    <div class="col-md-8">
        <p class="recipe-description">${recipe.shortDescription}</p>

        <strong>Ingredients:</strong>
        <table class="table">
            <tbody>
            <c:forEach var="ingredient" items="${recipe.ingredients}">
                <tr>
                    <td class="amount"><c:out value="${ingredient.amount}" /> <c:out value="${ingredient.unit}" /></td>
                    <td><c:out value="${ingredient.name}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="recipe-information">
            <span>Serving: ${recipe.serving}</span>
            <span>Difficulty: ${recipe.difficulty.displayName}</span>
            <span><span class="glyphicon glyphicon-fire"></span>Calories: ${recipe.calories}</span>
            <span><span class="glyphicon glyphicon-time"></span>Working time: ${recipe.workingTime}</span>
            <span><span class="glyphicon glyphicon-time"></span>Cooking time: ${recipe.cookingTime}</span>
            <span><span class="glyphicon glyphicon-time"></span>Resting time: ${recipe.restTime}</span>

        </div>

        <strong>Preparation:</strong>
        <div class="well well-sm">
            ${recipe.preparation}
        </div>
    </div>
    <div class="col-md-4">
        <div class="recipe-detail-rightside recipe-thumbnail">
            <img src="${recipe.imageLink}" alt="recipe-image">
        </div>
        <div class="recipe-detail-rightside">
            <cooky:rating rating="${recipe.rating}" maxRating="${recipe.maxRating}" />
        </div>
        <div class="recipe-detail-rightside recipe-author">
            <!-- TODO provide link to user profile, when profiles are implemented -->
            <span>Author: <a href="#"><c:out value="${recipe.author.name}" /></a></span>

            <sec:authorize access="isAuthenticated()">
                <c:set var="username">
                    <sec:authentication property="principal.username" />
                </c:set>

                <c:if test="${recipe.author.name eq username}">
                    <a href="/recipes/edit/${recipe.id}" class="btn btn-primary">Edit your recipe</a>
                </c:if>
            </sec:authorize>
        </div>
    </div>
</div>
