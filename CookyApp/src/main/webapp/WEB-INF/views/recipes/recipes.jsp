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
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet" />
    <link href="<c:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <title>Recipes</title>
</head>
<body>
<div class="container">
    <div class="col-md-12">
        <form id="addRecipe" action="/recipes/goToAddRecipe" method="post">
            <button type="submit" name="addBtn" class="btn btn-primary btn-block">Add Recipe</button>
            <sec:csrfInput/>
        </form>
    </div>
    <div class="row">
        <ul>
            <c:forEach var="recipes" items="${recipesList}">
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img src="${recipes.imageFileName}" alt="ALT NAME" class="img-responsive" />

                        <div class="caption">
                            <h3>${recipes.name}</h3>

                            <form id="editRecipe" action="/recipes/goToEditRecipe" method="post">
                                <p align="center">
                                    <button type="submit" class="btn btn-primary btn-block">Open</button>
                                </p>
                                <input type="hidden" name="id" value="${recipes.id}">
                                <sec:csrfInput/>
                            </form>
                            <form id="removeRecipe" action="/recipes/removeRecipe" method="post">
                                <p align="center">
                                    <button type="submit" class="btn btn-primary btn-block">Remove</button>
                                </p>
                                <input type="hidden" name="id" value="${recipes.id}">
                                <sec:csrfInput/>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
