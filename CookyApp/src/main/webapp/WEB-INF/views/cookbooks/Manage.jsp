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
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" href="#collapsePrivate">Private Cookbooks</a>
                    <span class="glyphicon glyphicon-user"></span>
                </h4>
            </div>
            <div id="collapsePrivate" class="panel-collapse collapse">
                <ul class="list-group cooky-cookbooks">
                    <c:forEach var="currentCookbook" items="${overview.privateCookbooks}">
                        <jsp:setProperty name="cookbook" property="name" value="${currentCookbook.name}" />
                        <jsp:setProperty name="cookbook" property="shortDescription"
                                         value="${currentCookbook.shortDescription}" />
                        <li class="list-group-item">
                            <form:form action="/cookbooks/manage/save" method="post" commandName="cookbook">
                                <cooky:cookbookItem item="${currentCookbook}" />
                            </form:form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" href="#collapseShared">Shared Cookbooks</a>
                    <span class="glyphicon glyphicon-share-alt"></span>
                </h4>
            </div>
            <div id="collapseShared" class="panel-collapse collapse">
                <ul class="list-group cooky-cookbooks">
                    <c:forEach var="currentCookbook" items="${overview.sharedCookbooks}">
                        <jsp:setProperty name="cookbook" property="name" value="${currentCookbook.name}" />
                        <jsp:setProperty name="cookbook" property="shortDescription"
                                         value="${currentCookbook.shortDescription}" />
                        <li class="list-group-item">
                            <form:form action="/cookbooks/manage/save" method="post" commandName="cookbook">
                                <cooky:cookbookItem item="${currentCookbook}" />
                            </form:form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" href="#collapsePublic">Public Cookbooks</a>
                    <span class="glyphicon glyphicon-eye-open"></span>
                </h4>
            </div>
            <div id="collapsePublic" class="panel-collapse collapse">
                <ul class="list-group cooky-cookbooks">
                    <c:forEach var="currentCookbook" items="${overview.publicCookbooks}">
                        <jsp:setProperty name="cookbook" property="name" value="${currentCookbook.name}" />
                        <jsp:setProperty name="cookbook" property="shortDescription"
                                         value="${currentCookbook.shortDescription}" />
                        <li class="list-group-item">
                            <form:form action="/cookbooks/manage/save" method="post" commandName="cookbook">
                                <cooky:cookbookItem item="${currentCookbook}" />
                            </form:form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<script>
    $(function() {
        $('.cooky-cookbook input').hide();
        $('textarea').hide();
        $('.cooky-cookbook-visibility').hide();
        $('.cooky-cookbook-saveButton').hide();

        $('.cooky-cookbooks').on('click', '.cooky-cookbook-editButton', function(event) {
            var closestCookbookParent = $(event.target).closest('.cooky-cookbook');
            closestCookbookParent.find('.cooky-cookbook-saveButton').show();
            closestCookbookParent.find('.cooky-cookbook-visibility').show();
            closestCookbookParent.find('textarea').show();
            closestCookbookParent.find('input').show();
            closestCookbookParent.find('h3').hide();
            closestCookbookParent.find('p').hide();
        });
        $('.cooky-cookbooks').on('click', '.cooky-cookbook-saveButton', function(event) {
            var closestCookbookParent = $(event.target).closest('.cooky-cookbook');
            closestCookbookParent.find('.cooky-cookbook-saveButton').hide();
            closestCookbookParent.find('.cooky-cookbook-visibility').hide()
            closestCookbookParent.find('textarea').hide();
            closestCookbookParent.find('input').hide();
            closestCookbookParent.find('h3').show();
            closestCookbookParent.find('p').show();
        });
    });
</script>