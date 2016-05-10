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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:eval expression="cookbook.visibility == T(de.cookyapp.enums.CookbookVisibility).PUBLIC" var="isPublic" />
<spring:eval expression="cookbook.visibility == T(de.cookyapp.enums.CookbookVisibility).PRIVATE" var="isPrivate" />
<spring:eval expression="cookbook.visibility == T(de.cookyapp.enums.CookbookVisibility).FRIENDS" var="isShared" />

<div id="cookbookDetail">
    <h1>${cookbook.name}</h1>
    <p>${cookbook.shortDescription}</p>

    <c:if test="${isPublic}">
        <span>This cookbook is visible to all Cookys.</span>
    </c:if>
    <c:if test="${isPrivate}">
        <span>This cookbook is only visibile to you.</span>
    </c:if>
    <c:if test="${isShared}">
        <span>This cookbook is visible to all your Cooky Friends.</span>
    </c:if>

    <div class="list-group">
        <c:forEach var="recipeVar" items="${cookbook.recipes}">
            <jsp:setProperty name="recipe" property="id" value="${recipeVar.id}" />

            <spring:url var="recipeUrl" value="/recipes/view/{recipeId}">
                <spring:param name="recipeId" value="${recipe.id}" />
            </spring:url>
            <spring:url var="removeRecipeUrl" value="/cookbooks/removeRecipe" />
            <spring:url var="moveRecipeUrl" value="/cookbooks/moveRecipe" />

            <div class="list-group-item">

                <div class="row">
                    <div class="col-md-9">
                        <a href="${recipeUrl}">
                            <h3 class="list-group-item-heading">${recipeVar.name}</h3>
                        </a>
                        <span>
                            <c:forEach begin="1" end="${recipeVar.rating}">
                                <span class="glyphicon glyphicon-star cooky-recipeRating"></span>
                            </c:forEach>
                            <c:forEach begin="${recipeVar.rating + 1}" end="${recipeVar.maxRating}">
                                <span class="glyphicon glyphicon-star-empty cooky-recipeRating"></span>
                            </c:forEach>
                        </span>
                        <p>${recipeVar.description}</p>
                    </div>
                    <div class="col-md-3">
                        <form:form method="post" action="${removeRecipeUrl}" commandName="recipe">
                            <form:hidden path="id" />
                            <form:hidden path="containingCookbook.id" />
                            <button type="submit" class="btn btn-default">Remove</button>
                        </form:form>
                        <form:form method="post" action="${moveRecipeUrl}" commandName="recipe">
                            <form:hidden path="id" />
                            <form:hidden path="containingCookbook.id" />
                            <form:label path="movedToCookbook.id">Move recipe to cookbook:</form:label>

                            <div class="input-group">
                                <form:select path="movedToCookbook.id" items="${cookbookOverview.cookbooks}"
                                             itemLabel="name" itemValue="id" cssClass="form-control">
                                </form:select>
                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-default">Move</button>
                                </span>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>
</div>