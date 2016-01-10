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

<div class="container">
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
                        <td><form:label path="name">Name:</form:label></td>
                        <td><form:input path="name" /></td>
                        <td><form:errors path="name" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="shortDescription">Short Description:</form:label></td>
                        <td><form:input path="shortDescription" /></td>
                        <td><form:errors path="shortDescription" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="preparation">Preparation:</form:label></td>
                        <td><form:input path="preparation" /></td>
                        <td><form:errors path="preparation" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="workingTime">Working Time (in minutes):</form:label></td>
                        <td><form:input path="workingTime" /></td>
                        <td><form:errors path="workingTime" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="cookingTime">Cooking Time (in minutes):</form:label></td>
                        <td><form:input path="cookingTime" /></td>
                        <td><form:errors path="cookingTime" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="difficulty">Difficulty:</form:label></td>
                        <td><form:select path="difficulty">
                            <form:option value="" label="Bitte auswählen..." />
                            <form:options items="${availableDifficulty}" itemLabel="recipeDifficulty" />
                        </form:select></td>
                        <td><form:errors path="difficulty" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="serving">Serving:</form:label></td>
                        <td><form:input path="serving" /></td>
                        <td><form:errors path="serving" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="calories">Calories:</form:label></td>
                        <td><form:input path="calories" /></td>
                        <td><form:errors path="calories" cssClass="formError" /></td>
                    </tr>
                    <tr>
                        <td><form:label path="restTime">Rest Time:</form:label></td>
                        <td><form:input path="restTime" /></td>
                        <td><form:errors path="restTime" cssClass="formError" /></td>
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
                        <th>Amount</th>
                        <th>Unit</th>
                    </tr>
                    </thead>
                    <tbody id="ingredientsBody">
                    <tr>
                        <td><form:input path="ingredients[0].name" /></td>
                        <td><form:input path="ingredients[0].amount" /></td>
                        <td><form:input path="ingredients[0].unit" /></td>
                    </tr>
                    </tbody>
                </table>
                <button class="btn btn-default" type="button" onclick="addRow()">Add another Ingredient</button>
            </div>
        </div>
    </form:form>
</div>