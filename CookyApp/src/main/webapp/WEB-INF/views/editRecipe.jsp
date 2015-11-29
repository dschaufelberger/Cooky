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
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css" />" rel="stylesheet"/>
    <link href="<c:url value="/resources/css/cooky/big-picture-css.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery/jquery-1.11.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
    <title>Edit Recipe</title>
</head>
<body>
<div class="col-md-6">
    <form action="editRecipeFinish" method="post">
        <div class="input-group">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Recipe Name</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><label>Name</label></td>
                    <td><input class="form-control" type="text" value="${recipe.name}" name="editName"></td>
                </tr>
                <tr>
                    <td><label>Short Description</label></td>
                    <td><input class="form-control" type="text" value="${recipe.shortDescription}"
                               name="editShortDescription"></td>
                </tr>
                <tr>
                    <td><label>Preparation</label></td>
                    <td><input class="form-control" type="text" value="${recipe.preparation}"
                               name="editPreparation"></td>
                </tr>
                <tr>
                    <td><label>Working Time (in minutes)</label></td>
                    <td><input class="form-control" type="text" value="${recipe.workingTime}"
                               name="editWorkingTime"></td>
                </tr>
                <tr>
                    <td><label>Cooking Time (in minutes)</label></td>
                    <td><input class="form-control" type="text" value="${recipe.cookingTime}"
                               name="editCookingTime"></td>
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
        <input type="hidden" value="${recipe.id}" name="id">
    </form>
</div>
</body>
</html>
