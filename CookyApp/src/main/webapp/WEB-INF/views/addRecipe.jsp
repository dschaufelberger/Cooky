<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 28.11.2015
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/js/recipeJS/recipes.js" />"></script>
    <title>Add Recipe</title>
</head>
<body>
<form id="addRecipe" action="addRecipe" method="post">
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
                    <td><label>Name</label></td>
                    <td><input type="text" name="recipeName" placeholder="Name"></td>
                </tr>
                <tr>
                    <td><label>Short Description</label></td>
                    <td><input type="text" name="shortDescription" placeholder="Short Description"></td>
                </tr>
                <tr>
                    <td><label>Preparation</label></td>
                    <td><input type="text" name="preparation" placeholder="Preparation"></td>
                </tr>
                <tr>
                    <td><label>Working Time (in minutes)</label></td>
                    <td><input type="text" name="workingTime" placeholder="Working Time"></td>
                </tr>
                <tr>
                    <td><label>Cooking Time (in minutes)</label></td>
                    <td><input type="text" name="cookingTime" placeholder="Cooking Time"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add"></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="col-md-6">
    <div class="input-group">
        <table class="table table-hover ingredientTable">
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
        <button type="button" onclick="addRow()">Add another Ingredient</button>
    </div>
</div>
</form>
</body>
</html>
