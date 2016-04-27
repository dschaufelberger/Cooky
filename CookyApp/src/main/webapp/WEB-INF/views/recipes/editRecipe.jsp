<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 27.11.2015
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="<spring:url value="/resources/js/recipeJS/recipes.js" />"></script>

<div class="container">
    <form:form action="/recipes/editRecipe" method="post" commandName="recipe">
        <form:hidden path="id" />
        <div class="col-md-6">
            <div class="input-group">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Recipe Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><form:label path="name">Name:</form:label></td>
                        <td><form:input path="name" name="editName" /></td>
                        <td><form:errors path="name" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="shortDescription">Short Description:</form:label></td>
                        <td><form:textarea path="shortDescription" value="${recipe.shortDescription}"
                                           name="editShortDescription" /></td>
                        <td><form:errors path="shortDescription" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="preparation">Preparation:</form:label></td>
                        <td><form:textarea path="preparation" value="${recipe.preparation}"
                                           name="editPreparation" /></td>
                        <td><form:errors path="preparation" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="workingTime">Working Time (in minutes):</form:label></td>
                        <td><form:input path="workingTime" value="${recipe.workingTime}"
                                        name="editWorkingTime" /></td>
                        <td><form:errors path="workingTime" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="cookingTime">Cooking Time (in minutes):</form:label></td>
                        <td><form:input path="cookingTime" value="${recipe.cookingTime}"
                                        name="editCookingTime" /></td>
                        <td><form:errors path="cookingTime" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="restTime">Rest Time:</form:label></td>
                        <td><form:input path="restTime" value="${recipe.restTime}" /></td>
                        <td><form:errors path="restTime" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="calories">Calories:</form:label></td>
                        <td><form:input path="calories" value="${recipe.calories}" /></td>
                        <td><form:errors path="calories" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="serving">Serving:</form:label></td>
                        <td><form:input path="serving" value="${recipe.serving}" /></td>
                        <td><form:errors path="calories" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="difficulty">Difficulty:</form:label></td>
                        <td><form:select path="difficulty">
                            <form:option value="" label="${recipe.difficulty.name().toLowerCase()}" />
                            <form:options items="${availableDifficulty}" itemLabel="recipeDifficulty" />
                        </form:select></td>
                        <td><form:errors path="difficulty" cssClass="formError" /></td>
                    </tr>
             <span class="input-group-btn">
                 <tr>
                     <td>
                         <button class="btn btn-default" type="submit">Finish</button>
                     </td>
                 </tr>
        </span>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-6">
            <div class="input-group">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Ingredients</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach varStatus="loop" var="ingredient" items="${recipe.ingredients}">
                        <tr>
                            <td><input name="ingredients[${loop.index}].name" value="${ingredient.name}"></td>
                            <td><input name="ingredients[${loop.index}].amount" value="${ingredient.amount}"></td>
                            <td><input name="ingredients[${loop.index}].unit" value="${ingredient.unit}"></td>
                            <td><input name="ingredients[${loop.index}].id" value="${ingredient.id}" type="hidden"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </form:form>
   <form:form action="/recipes/rateRecipe">
       <input type="hidden" name="id" value="${recipe.id}">
       <input type="hidden" class="currentRating" name="currentRating" value="${recipe.rating}">
        <table>
            <tr>
                <td><button type="submit" class="buttonTransparent" value="1" name="rating"><img class="ratings_stars star1"></button></td>
                <td><button type="submit" class="buttonTransparent" value="2" name="rating"><img class="ratings_stars star2"></button></td>
                <td><button type="submit" class="buttonTransparent" value="3" name="rating"><img class="ratings_stars star3"></button></td>
                <td><button type="submit" class="buttonTransparent" value="4" name="rating"><img class="ratings_stars star4"></button></td>
                <td><button type="submit" class="buttonTransparent" value="5" name="rating"><img class="ratings_stars star5"></button></td>
            </tr>
        </table>
   </form:form>
</div>

