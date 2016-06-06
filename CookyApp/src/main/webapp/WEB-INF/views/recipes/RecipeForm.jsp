<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 03.06.2016
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<script src="<spring:url value="/resources/js/cooky/recipes.js" />"></script>

<c:set var="formUrl">
    <tiles:getAsString name="formUrl" />
</c:set>

<h2><tiles:insertAttribute name="pageHeadline" /></h2>


<form:form action="${formUrl}" method="post" commandName="recipe" enctype="multipart/form-data"
           cssClass="form-horizontal">
    <form:hidden path="id" />
    <div class="col-md-8">
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="name">Name:</form:label>
            <div class="col-sm-6">
                <form:input path="name" name="editName" cssClass="form-control" />
                <form:errors path="name" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="shortDescription">Short Description:</form:label>
            <div class="col-sm-8">
                <form:textarea path="shortDescription" value="${recipe.shortDescription}"
                               name="editShortDescription" rows="4" cssClass="form-control" />
                <form:errors path="shortDescription" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="preparation">Preparation:</form:label>
            <div class="col-sm-8">
                <form:textarea path="preparation" value="${recipe.preparation}"
                               name="editPreparation" rows="10" cssClass="form-control" />
                <form:errors path="preparation" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="workingTime">Working Time (in minutes):</form:label>
            <div class="col-sm-2">
                <form:input path="workingTime" value="${recipe.workingTime}"
                            name="editWorkingTime" cssClass="form-control" />
                <form:errors path="workingTime" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="cookingTime">Cooking Time (in minutes):</form:label>
            <div class="col-sm-2">
                <form:input path="cookingTime" value="${recipe.cookingTime}"
                            name="editCookingTime" cssClass="form-control" />
                <form:errors path="cookingTime" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="restTime">Rest Time (in minutes):</form:label>
            <div class="col-sm-2">
                <form:input path="restTime" value="${recipe.restTime}" cssClass="form-control" />
                <form:errors path="restTime" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="calories">Calories:</form:label>
            <div class="col-sm-2">
                <form:input path="calories" value="${recipe.calories}" cssClass="form-control" />
                <form:errors path="calories" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="serving">Serving:</form:label>
            <div class="col-sm-2">
                <form:input path="serving" value="${recipe.serving}" cssClass="form-control" />
                <form:errors path="calories" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <form:label cssClass="control-label col-sm-2" path="difficulty">Difficulty:</form:label>
            <div class="col-sm-3">
                <form:select path="difficulty" cssClass="form-control">
                    <%-- NONE is an existing value for the recipe difficulty but no valid selection --%>
                    <form:option value="NONE" label="Please select..." />
                    <form:options items="${availableDifficulties}" itemLabel="displayName" />
                </form:select>
                <form:errors path="difficulty" cssClass="cooky-formError" />
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Recipe Image:</label>
            <div class="col-sm-6">
                <label class="btn btn-default">
                    Select an image..<input id="imageFileUpload" type="file" name="recipeImage" accept="image/jpeg" />
                </label>
                <form:errors path="imageLink" cssClass="cooky-formError" />
            </div>
        </div>

        <div id="recipe-form-ingredients" class="row">
            <div id="recipe-form-ingredientsLabel" class="col-md-2">
                <label>Ingredients: </label>
            </div>
            <div class="col-md-8">
                <table id="recipe-ingredient-table" class="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Amount</th>
                        <th>Unit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ingredient" items="${recipe.ingredients}" varStatus="loop">
                        <tr>
                            <td><input name="ingredients[${loop.index}].name" value="${ingredient.name}"
                                       class="form-control" class="form-control"></td>
                            <td><input name="ingredients[${loop.index}].amount" value="${ingredient.amount}"
                                       class="form-control"></td>
                            <td><input name="ingredients[${loop.index}].unit" value="${ingredient.unit}"
                                       class="form-control"></td>
                            <td><input name="ingredients[${loop.index}].id" value="${ingredient.id}" type="hidden"></td>
                        </tr>
                    </c:forEach>
                    <c:set var="ingredientListSize" value="${recipe.ingredients.size()}" />
                    <c:if test="${ingredientListSize > 0}">
                        <tr>
                            <td><input name="ingredients[${ingredientListSize}].name" class="form-control"></td>
                            <td><input name="ingredients[${ingredientListSize}].amount" class="form-control"></td>
                            <td><input name="ingredients[${ingredientListSize}].unit" class="form-control"></td>
                        </tr>
                    </c:if>
                    <c:if test="${ingredientListSize == 0}">
                        <c:forEach begin="1" end="8" varStatus="loop">
                            <tr>
                                <td><input name="ingredients[${loop.index - 1}].name" class="form-control"></td>
                                <td><input name="ingredients[${loop.index - 1}].amount" class="form-control"></td>
                                <td><input name="ingredients[${loop.index - 1}].unit" class="form-control"></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Save</button>
            </div>
        </div>
    </div>
</form:form>

<script>
    $('#recipe-ingredient-table').on("change", this, function (eventArgs) {
        debugger;
        var ingredientNames = $('tr td input[name$=".name"]');
        var leastOneEmpty = false;
        ingredientNames.each(function (index, item) {
            leastOneEmpty = leastOneEmpty || !$(item).val().trim();
        });

        if (!leastOneEmpty) {
            var ingredientAmount = ingredientNames.length;
            var tableRow = '<tr>' +
                    '<td><input name="ingredients[' + ingredientAmount + '].name" class="form-control"></td>' +
                    '<td><input name="ingredients[' + ingredientAmount + '].amount" class="form-control"></td>' +
                    '<td><input name="ingredients[' + ingredientAmount + '].unit" class="form-control"></td>' +
                    '</tr>';
            var table = eventArgs.currentTarget;
            $('tbody', table).append(tableRow);
        }
    });
</script>