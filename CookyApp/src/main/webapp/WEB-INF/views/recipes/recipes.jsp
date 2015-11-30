<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 27.11.2015
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <title>Recipes</title>
</head>
<body>
<div class="col-md-6">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Recipes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="recipes" items="${recipesList}">
            <tr>
                <td><span id="showRecipe">${recipes.name}</span></td>
                <form id="removeRecipe" action="/recipes/removeRecipe" method="post">
                    <td>
                        <button type="submit" name="deleteBtn">Delete</button>
                    </td>
                    <input type="hidden" name="id" value="${recipes.id}">
                </form>
                <form id="editRecipe" action="/recipes/goToEditRecipe" method="post">
                    <td>
                        <button type="submit" name="editBtn">Edit</button>
                    </td>
                    <input type="hidden" name="id" value="${recipes.id}">
                </form>
            </tr>
        </c:forEach>
        <tr>
            <form id="addRecipe" action="/recipes/goToAddRecipe" method="post">
                <td>
                    <button type="submit" name="addBtn">New Recipe</button>
                </td>
            </form>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
