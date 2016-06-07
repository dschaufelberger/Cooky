<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 23.04.2016
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ attribute name="item" type="de.cookyapp.web.viewmodel.cookbook.Cookbook" required="true" %>

<jsp:setProperty name="cookbook" property="id" value="${item.id}" />
<jsp:setProperty name="cookbook" property="name" value="${item.name}" />
<jsp:setProperty name="cookbook" property="shortDescription"
                 value="${item.shortDescription}" />
<jsp:setProperty name="cookbook" property="visibility" value="${item.visibility}" />

<spring:url var="recipeUrl" value="/cookbooks/view/{cookbookId}">
    <spring:param name="cookbookId" value="${cookbook.id}" />
</spring:url>

<form:form action="/cookbooks/manage/save" method="post" commandName="cookbook">
    <div class="cooky-cookbook">
        <form:hidden path="id" />
        <div class="row">
            <div class="col-md-8">
                <div class="form-group">
                    <a href="${recipeUrl}">
                        <h3>${item.name}</h3>
                    </a>
                    <form:input path="name" cssClass="form-control" />
                </div>
                <div class="form-group">
                    <p>${item.shortDescription}</p>
                    <form:textarea path="shortDescription" cssClass="form-control" />
                </div>
                <span>${item.recipeCount} Recipe(s) in this cookbook.</span>
            </div>
            <div class="col-md-4">
                <div class="btn-group-vertical">
                    <button type="submit" form="deleteForm${item.id}" class="btn">
                        <span class="glyphicon glyphicon-trash"></span> Remove
                    </button>
                    <button type="button" class="btn cooky-cookbook-editButton">
                        <span class="glyphicon glyphicon-edit"></span> Edit
                    </button>
                    <button type="submit" class="btn cooky-cookbook-saveButton">
                        <span class="glyphicon glyphicon-check"></span> Save
                    </button>
                </div>
                <div class="form-group cooky-cookbook-visibility">
                    <form:select path="visibility" cssClass="form-control" items="${item.visibilities}"
                                 itemLabel="displayName" />
                </div>
            </div>
        </div>
    </div>
</form:form>
<form id="deleteForm${item.id}" method="post" action="/cookbooks/manage/delete/${item.id}">
    <sec:csrfInput />
</form>