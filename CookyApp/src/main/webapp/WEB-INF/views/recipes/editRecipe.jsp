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
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <title>Edit Recipe</title>
</head>
<body>
<div class="col-md-6">
    <form:form action="/recipes/editRecipe" method="post" commandName="recipe">
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
                    <td><form:input path="name" value="${recipeForId.name}" name="editName"/></td>
                    <td><form:errors path="name" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="shortDescription">Short Description:</form:label></td>
                    <td><form:input path="shortDescription" value="${recipeForId.shortDescription}"
                               name="editShortDescription"/></td>
                    <td><form:errors path="shortDescription" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="preparation">Preparation:</form:label></td>
                    <td><form:input path = "preparation" value="${recipeForId.preparation}"
                               name="editPreparation"/></td>
                    <td><form:errors path="preparation" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="workingTime">Working Time (in minutes):</form:label></td>
                    <td><form:input path="workingTime" value="${recipeForId.workingTime}"
                               name="editWorkingTime"/></td>
                    <td><form:errors path="workingTime" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="cookingTime">Cooking Time (in minutes):</form:label></td>
                    <td><form:input path="cookingTime" value="${recipeForId.cookingTime}"
                               name="editCookingTime"/></td>
                    <td><form:errors path="cookingTime" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="calories">Calories:</form:label></td>
                    <td><form:input path="calories" value="${recipeForId.calories}"/></td>
                    <td><form:errors path="calories" cssClass="formError"/> </td>
                </tr>
                <tr>
                    <td><form:label path="serving">Serving:</form:label></td>
                    <td><form:input path="serving" value="${recipeForId.serving}"/> </td>
                    <td><form:errors path="calories" cssClass="formError"/> </td>
                </tr>
                <tr>
                    <td><form:label path="difficulty">Difficulty:</form:label></td>
                    <td><form:select path="difficulty">
                        <form:option value="" label="${recipeForId.difficulty.name()}"/>
                        <form:options items="${availableDifficulty}" itemLabel="recipeDifficulty" />
                    </form:select></td>
                    <td><form:errors path="difficulty" cssClass="formError" /></td>
                </tr>
                    <span class="input-group-btn">
                             <tr>
                                 <td>
                                     <button class="btn btn-success" type="submit">Finish</button>
                                 </td>
                             </tr>
                        </span>
                </tbody>
            </table>
        </div>
        <input type="hidden" value="${recipeForId.id}" name="id">
    </form:form>
</div>
</body>
</html>
