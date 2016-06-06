<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cooky" uri="http://cookyapp.de/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<spring:url value="/resources/js/cooky/recipes.js" />"></script>
<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 27.05.2016
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
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
        <div class="recipe-information">
            <span><span class="glyphicon glyphicon-time"></span>All times in minutes</span>
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
            <sec:authorize access="isAuthenticated()" var="userIsAuthenticated">
                <input type="hidden" name="id" class="recipeId" value="${recipe.id}">

                <div class="rating">
                    <c:set var="recipeRating" scope="request" value="${recipe.rating}" />
                    <c:set var="count" value="1" />
                    <c:forEach begin="1" end="${recipeRating}">
                        <span id="${count}" class="glyphicon glyphicon-star cooky-recipeRating ratings_stars"
                              onclick="rate(this.id)">
                        </span>
                        <c:set var="count" value="${count + 1}" />
                    </c:forEach>
                    <c:forEach begin="${recipeRating + 1}" end="5">
                 <span id="${count}" class="glyphicon glyphicon-star-empty cooky-recipeRating ratings_stars"
                       onclick="rate(this.id)">
                 </span>
                        <c:set var="count" value="${count + 1}" />
                    </c:forEach>
                </div>
                Rate this recipe!
            </sec:authorize>

            <c:if test="${not userIsAuthenticated}">
                <cooky:rating rating="${recipe.rating}" maxRating="${recipe.maxRating}" />
            </c:if>
        </div>


        <div class="recipe-detail-rightside recipe-author-action">
            <!-- TODO provide link to user profile, when profiles are implemented -->
            <span>Author: <a href="#"><c:out value="${recipe.author.name}" /></a></span>

            <sec:authorize access="${userIsAuthenticated}">
                <c:set var="username">
                    <sec:authentication property="principal.username" />
                </c:set>

                <c:if test="${recipe.author.name eq username}">
                    <a href="/recipes/edit/${recipe.id}" class="btn btn-primary">Edit</a>

                    <form id="removeRecipe" action="/recipes/remove" method="post">
                        <button type="submit" class="btn btn-primary btn-block">Remove</button>
                        <input type="hidden" name="id" value="${recipe.id}">
                        <sec:csrfInput />
                    </form>
                </c:if>
            </sec:authorize>
        </div>

        <sec:authorize access="${userIsAuthenticated}">
            <div class="recipe-detail-rightside">
                <spring:url var="addRecipeUrl" value="/cookbooks/addRecipe" />
                <form:form method="post" action="${addRecipeUrl}" commandName="cookbook">
                    <form:hidden path="recipeId" />
                    <form:label path="cookbookId">Add recipe to cookbook:</form:label>

                    <div class="input-group">
                        <form:select path="cookbookId" items="${cookbookOverview.cookbooks}"
                                     itemLabel="name" itemValue="id" cssClass="form-control">
                        </form:select>
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-primary">Add</button>
                        </span>
                    </div>
                </form:form>
            </div>
        </sec:authorize>
    </div>
</div>
