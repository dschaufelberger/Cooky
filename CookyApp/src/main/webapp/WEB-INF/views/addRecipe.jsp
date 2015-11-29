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
    <title>Add Recipe</title>
</head>
<body>
    <div class="container">
        <div class="col-md-6">
            <div class="input-group">
                <table>
                    <form id="addRecipe" action="addRecipe" method="post">
                        <input type="text" name="recipeName" placeholder="Name">
                        <input type="text" name="ingredientName" placeholder="Ingredient Name">
                        <input type="text" name="shortDescription" placeholder="Short Description">
                        <input type="text" name="serving" placeholder="Serving Time">
                        <input type="text" name="preparation" placeholder="Preparation">
                        <input type="text" name="Calories" placeholder="Calories">
                        <input type="text" name="difficulty" placeholder="Difficulty">
                        <input type="text" name="workingTime" placeholder="Working Time">
                        <input type="text" name="cookingTime" placeholder="Cooking Time">
                        <input type="text" name="recipeIngredient" placeholder="Recipe Ingredient">
                    </form>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
