<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 23.04.2016
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="item" type="de.cookyapp.web.viewmodel.cookbook.Cookbook" required="true" %>

<div class="container cooky-cookbook">
    <div class="row">
        <div class="col-md-8">
            <h3>${item.name}</h3>
            <form:input path="name" cssClass="form-control"/>
            <p>${item.shortDescription}</p>
            <form:textarea path="shortDescription" cssClass="form-control"/>
        </div>
        <div class="col-md-4">
            <div class="container">
                <div class="btn-group-vertical">
                    <button type="button" class="btn btn-primary cooky-cookbook-editButton">Edit</button>
                    <button type="submit" class="btn btn-primary cooky-cookbook-saveButton">Save</button>
                </div>
                <div class="btn-group-vertical">
                    <button type="button" class="btn btn-primary">Remove</button>
                </div>
                <div class="btn-group-vertical cooky-cookbook-visibility">
                    <c:if test="${item.visibility != 'PUBLIC'}">
                        <button type="button" class="btn btn-primary">Make public
                        </button>
                    </c:if>
                    <c:if test="${item.visibility != 'PRIVATE'}">
                        <button type="button" class="btn btn-primary">Make private
                        </button>
                    </c:if>
                    <c:if test="${item.visibility != 'FRIENDS'}">
                        <button type="button" class="btn btn-primary">Make shared
                        </button>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>