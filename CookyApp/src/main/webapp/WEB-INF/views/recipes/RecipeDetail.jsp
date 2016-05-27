<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <p class="cooky-recipeDescription">${recipe.shortDescription}</p>

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

        <div class="well well-sm">
            ${recipe.preparation}
        </div>
    </div>
    <div class="col-md-4">
        <div class="recipe-detail-rightside recipe-thumbnail">
            <img src="${recipe.imageLink}" alt="recipe-image">
        </div>
        <div class="recipe-detail-rightside recipe-author">
            <!-- TODO provide link to user profile, when profiles are implemented -->
            <span>Author: <a href="#"><c:out value="${recipe.author.name}" /></a></span>
        </div>
    </div>
</div>
