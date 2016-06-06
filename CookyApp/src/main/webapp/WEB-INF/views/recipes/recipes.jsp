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

<sec:authorize access="isAuthenticated()" var="userIsAuthenticated">
    <div class="row">
        <div class="col-md-12">
            <a href="/recipes/add" class="btn btn-primary btn-block">Add Recipe</a>
        </div>
    </div>
</sec:authorize>

<c:if test="${userIsAuthenticated}">
    <c:set var="username">
        <sec:authentication property="principal.username" />
    </c:set>
</c:if>

<c:if test="${recipesList.size() == 0}">
    <span class="alert alert-info search-no-result">No Recipes were found, that matched the entered search term.</span>
</c:if>

<c:forEach var="recipe" items="${recipesList}" varStatus="loop">
    <c:if test="${userIsAuthenticated && (recipe.author.name eq username)}" var="isOwner" />

    <c:if test="${loop.index % 3 == 0}">
        <div class="row">
    </c:if>

    <div class="col-md-4">
        <div class="thumbnail">
            <img src="${recipe.imageLink}" alt="image">
            <div class="caption">
                <h3>${recipe.name}</h3>

                <div class="row">
                    <div class="${isOwner ? 'col-md-4' : 'col-md-12'}">
                        <a href="/recipes/view/${recipe.id}" class="btn btn-primary btn-block">View</a>
                    </div>

                    <c:if test="${isOwner}">

                        <div class="col-md-4">
                            <a href="/recipes/edit/${recipe.id}" class="btn btn-primary btn-block">Edit</a>
                        </div>

                        <div class="col-md-4">
                            <form id="removeRecipe" action="/recipes/remove" method="post">
                                <button type="submit" class="btn btn-primary btn-block">Remove</button>
                                <input type="hidden" name="id" value="${recipe.id}">
                                <sec:csrfInput />
                            </form>
                        </div>
                    </c:if>
                </div>

            </div>
        </div>
    </div>

    <c:if test="${(loop.index % 3 == 2) || loop.last}">
        </div>
    </c:if>
</c:forEach>
