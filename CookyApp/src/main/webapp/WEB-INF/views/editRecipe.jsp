<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 27.11.2015
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Recipe</title>
</head>
<body>
<div class="container">
    <div class="col-md-6">
        <form action="editRecipeFinish" method="post">
            <div class="input-group">
                <table>
                    <thead>
                    <tr>
                        <th>Recipe Name</th>
                        <th>Ingredient Unit</th>
                        <th>Ingredient Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input class="form-control" type="text" value="${recipe.name}" name="editName"></td>
                        <c:forEach var="ingredient" items="${ingredients}">
                            <td><input class="form-control" type="text" value="${ingredients.Unit}"
                                       name="ingredientUnit"></td>
                            <td><input class="form-control" type="text" value="${ingredients.Amount}"
                            name="ingredientAmount"></td>
                        </c:forEach>
                        <span class="input-group-btn">
                             <button class="btn btn-success" type="submit">Finish</button>
                        </span>
                    </tr>
                    </tbody>
                </table>
            </div>
            <input type="hidden" value="${recipe.id}" name="editedId">
        </form>
    </div>
</div>
</body>
</html>
