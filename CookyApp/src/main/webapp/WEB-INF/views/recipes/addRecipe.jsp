<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 28.11.2015
  Time: 18:54
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
    <script src="<c:url value="/resources/js/recipeJS/recipes.js" />"></script>
    <title>Add Recipe</title>
    <meta charset="UTF-8">
</head>
<body>
<form:form method="POST" action="/recipes/addRecipe" commandName="recipe">
<div class="col-md-6">
    <div class="input-group">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Recipe</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><form:label path="name" class="label label-default">Name:</form:label></td>
                    <td><form:input path="name"/></td>
                    <td><form:errors path="name" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="shortDescription" class="label label-default">Short Description:</form:label></td>
                    <td><form:input path="shortDescription"/></td>
                    <td><form:errors path="shortDescription" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="preparation" class="label label-default">Preparation:</form:label></td>
                    <td><form:input path="preparation"/></td>
                    <td><form:errors path="preparation" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="workingTime" class="label label-default">Working Time (in minutes):</form:label></td>
                    <td><form:input path="workingTime"/></td>
                    <td><form:errors path="workingTime" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="cookingTime" class="label label-default">Cooking Time (in minutes):</form:label></td>
                    <td><form:input path="cookingTime" /></td>
                    <td><form:errors path="cookingTime" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="difficulty" class="label label-default">Difficulty:</form:label></td>
                    <td><form:select path="difficulty">
                        <form:option value="" label="Bitte auswählen..." />
                        <form:options items="${availableDifficulty}" itemLabel="recipeDifficulty" />
                    </form:select></td>
                    <td><form:errors path="difficulty" cssClass="formError" /></td>
                </tr>
                <tr>
                    <td><form:label path="serving" class="label label-default">Serving:</form:label></td>
                    <td><form:input path="serving" /></td>
                    <td><form:errors path="serving" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><form:label path="calories" class="label label-default">Calories:</form:label></td>
                    <td><form:input path="calories" /></td>
                    <td><form:errors path="calories" cssClass="formError"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add" class="btn btn-default"></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="col-md-6">
    <div class="input-group">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Ingredient</th>
            </tr>
            </thead>
            <tbody id="ingredientsBody">
                <tr>
                    <td><input type="text" name="ingredient"></td>
                </tr>
            </tbody>
        </table>
        <button class="btn btn-default" type="button" onclick="addRow()">Add another Ingredient</button>
    </div>
</div>
</form:form>
</body>
</html>
