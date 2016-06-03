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
<script src="<spring:url value="/resources/js/recipeJS/recipes.js" />"></script>

<c:set var="formUrl">
    <tiles:insertAttribute name="formUrl" />
</c:set>

<form:form action="${formUrl}" method="post" commandName="recipe" enctype="multipart/form-data"
           cssClass="form-horizontal">
    <form:hidden path="id" />
    <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="name">Name:</form:label>
        <div class="col-sm-10">
            <form:input path="name" name="editName" cssClass="form-control" />
            <form:errors path="name" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="shortDescription">Short Description:</form:label>
        <div class="col-sm-10">
            <form:textarea path="shortDescription" value="${recipe.shortDescription}"
                           name="editShortDescription" rows="4" cssClass="form-control" />
            <form:errors path="shortDescription" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="preparation">Preparation:</form:label>
        <div class="col-sm-10">
            <form:textarea path="preparation" value="${recipe.preparation}"
                           name="editPreparation" rows="10" cssClass="form-control" />
            <form:errors path="preparation" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="recipe-form-short">
        <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="workingTime">Working Time (in minutes):</form:label>
        <div class="col-sm-10">
            <form:input path="workingTime" value="${recipe.workingTime}"
                        name="editWorkingTime" cssClass="form-control" />
            <form:errors path="workingTime" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="cookingTime">Cooking Time (in minutes):</form:label>
        <div class="col-sm-10">
            <form:input path="cookingTime" value="${recipe.cookingTime}"
                        name="editCookingTime" cssClass="form-control" />
            <form:errors path="cookingTime" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="restTime">Rest Time:</form:label>
        <div class="col-sm-10">
            <form:input path="restTime" value="${recipe.restTime}" cssClass="form-control" />
            <form:errors path="restTime" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="calories">Calories:</form:label>
        <div class="col-sm-10">
            <form:input path="calories" value="${recipe.calories}" cssClass="form-control" />
            <form:errors path="calories" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="serving">Serving:</form:label>
        <div class="col-sm-10">
            <form:input path="serving" value="${recipe.serving}" cssClass="form-control" />
            <form:errors path="calories" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label cssClass="control-label col-sm-2" path="difficulty">Difficulty:</form:label>
        <div class="col-sm-10">
            <form:select path="difficulty" cssClass="form-control">
                <%-- NONE is an existing value for the recipe difficulty but no valid selection --%>
                <form:option value="NONE" label="Please select..." />
                <form:options items="${availableDifficulties}" itemLabel="displayName" />
            </form:select>
            <form:errors path="difficulty" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Recipe Image</label>
            <div class="col-sm-10">
                <input type="file" name="recipeImage" accept="image/jpeg" />
                <form:errors path="imageLink" cssClass="cooky-formError" />
            </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="btn btn-default" type="submit">Save</button>
        </div>
    </div>
    </div>
</form:form>