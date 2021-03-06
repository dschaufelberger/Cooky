<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 23.04.2016
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cooky" uri="http://cookyapp.de/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--<c:if test="${overview.privateCookbooks.size() > 0}">--%>
<%--<c:set var="privateCollapsed" value=""--%>
<%--</c:if>--%>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapsePrivate">Private Cookbooks</a>
                <span class="glyphicon glyphicon-user"></span>
            </h4>
        </div>
        <div id="collapsePrivate" class="panel-collapse collapse ${overview.privateCookbooks.size() > 0 ? 'in' : ''}">
            <ul class="list-group cooky-cookbooks">
                <c:forEach var="currentCookbook" items="${overview.privateCookbooks}">
                    <li class="list-group-item">
                        <cooky:cookbookItem item="${currentCookbook}" />
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapseShared">Shared Cookbooks</a>
                <span class="glyphicon glyphicon-share-alt"></span>
            </h4>
        </div>
        <div id="collapseShared" class="panel-collapse collapse ${overview.sharedCookbooks.size() > 0 ? 'in' : ''}">
            <ul class="list-group cooky-cookbooks">
                <c:forEach var="currentCookbook" items="${overview.sharedCookbooks}">
                    <li class="list-group-item">
                        <cooky:cookbookItem item="${currentCookbook}" />
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapsePublic">Public Cookbooks</a>
                <span class="glyphicon glyphicon-eye-open"></span>
            </h4>
        </div>
        <div id="collapsePublic" class="panel-collapse collapse ${overview.publicCookbooks.size() > 0 ? 'in' : ''}">
            <ul class="list-group cooky-cookbooks">
                <c:forEach var="currentCookbook" items="${overview.publicCookbooks}">
                    <li class="list-group-item">
                        <cooky:cookbookItem item="${currentCookbook}" />
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-4">
        <div class="panel panel-default">
            <div class="panel-body">
                <form:form action="/cookbooks/manage/create" method="post" modelAttribute="newCookbook">
                    <div class="form-group">
                        <form:label path="name">Name:</form:label>
                        <form:input path="name" cssClass="form-control" placeholder="The Cookbook's name" />
                        <form:errors path="name" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
                    </div>
                    <div class="form-group">
                        <form:label path="shortDescription">Description</form:label>
                        <form:textarea path="shortDescription" cssClass="form-control"
                                       placeholder="A short description for the Cookbook ..." />
                        <form:errors path="shortDescription" cssClass="cooky-formError"
                                     element="div class=\"col-sm-10\"" />
                    </div>
                    <div class="form-group">
                        <form:label path="visibility">Visibility</form:label>
                        <form:select path="visibility" items="${newCookbook.visibilities}" cssClass="form-control"
                                     itemLabel="displayName" />
                        <form:errors path="visibility" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn">
                            <span class="glyphicon glyphicon-plus"></span> Create cookbook
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('.cooky-cookbook input').hide();
        $('.cooky-cookbook textarea').hide();
        $('.cooky-cookbook-visibility').hide();
        $('.cooky-cookbook-saveButton').hide();

        $('.cooky-cookbooks').on('click', '.cooky-cookbook-editButton', function (event) {
            var closestCookbookParent = $(event.target).closest('.cooky-cookbook');
            closestCookbookParent.find('.cooky-cookbook-saveButton').show();
            closestCookbookParent.find('.cooky-cookbook-visibility').show();
            closestCookbookParent.find('textarea').show();
            closestCookbookParent.find('input').show();
            closestCookbookParent.find('.cooky-cookbook-editButton').hide();
            closestCookbookParent.find('h3').hide();
            closestCookbookParent.find('p').hide();
        });
        $('.cooky-cookbooks').on('click', '.cooky-cookbook-saveButton', function (event) {
            var closestCookbookParent = $(event.target).closest('.cooky-cookbook');
            closestCookbookParent.find('.cooky-cookbook-saveButton').hide();
            closestCookbookParent.find('.cooky-cookbook-visibility').hide()
            closestCookbookParent.find('textarea').hide();
            closestCookbookParent.find('input').hide();
            closestCookbookParent.find('.cooky-cookbook-editButton').show();
            closestCookbookParent.find('h3').show();
            closestCookbookParent.find('p').show();
        });
    });
</script>