<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 23.04.2016
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <ul class="list-group">
                    <c:forEach var="cookbook" items="${overview.privateCookbooks}">
                        <li class="list-group-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-8">
                                        <h3>${cookbook.name}</h3>
                                        <p>${cookbook.shortDescription}</p>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="container">
                                            <div class="btn-group-vertical">
                                                <button type="button" class="btn btn-primary">Edit</button>
                                                <button type="button" class="btn btn-primary">Save</button>
                                            </div>
                                            <div class="btn-group-vertical">
                                                <button type="button" class="btn btn-primary">Remove</button>
                                            </div>
                                            <div class="btn-group-vertical">
                                                <c:if test="${cookbook.visibility != 'PUBLIC'}">
                                                    <button type="button" class="btn btn-primary">Make public</button>
                                                </c:if>
                                                <c:if test="${cookbook.visibility != 'PRIVATE'}">
                                                    <button type="button" class="btn btn-primary">Make private</button>
                                                </c:if>
                                                <c:if test="${cookbook.visibility != 'FRIENDS'}">
                                                    <button type="button" class="btn btn-primary">Make shared</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
                <ul class="list-group">
                    <c:forEach var="cookbook" items="${overview.sharedCookbooks}">
                        <li class="list-group-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-8">
                                        <h3>${cookbook.name}</h3>
                                        <p>${cookbook.shortDescription}</p>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="container">
                                            <div class="btn-group-vertical">
                                                <button type="button" class="btn btn-primary">Edit</button>
                                                <button type="button" class="btn btn-primary">Save</button>
                                            </div>
                                            <div class="btn-group-vertical">
                                                <button type="button" class="btn btn-primary">Remove</button>
                                            </div>
                                            <div class="btn-group-vertical">
                                                <c:if test="${cookbook.visibility != 'PUBLIC'}">
                                                    <button type="button" class="btn btn-primary">Make public</button>
                                                </c:if>
                                                <c:if test="${cookbook.visibility != 'PRIVATE'}">
                                                    <button type="button" class="btn btn-primary">Make private</button>
                                                </c:if>
                                                <c:if test="${cookbook.visibility != 'FRIENDS'}">
                                                    <button type="button" class="btn btn-primary">Make shared</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
                <ul class="list-group">
                    <c:forEach var="cookbook" items="${overview.publicCookbooks}">
                        <li class="list-group-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-8">
                                        <h3>${cookbook.name}</h3>
                                        <p>${cookbook.shortDescription}</p>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="container">
                                            <div class="btn-group-vertical">
                                                <button type="button" class="btn btn-primary">Edit</button>
                                                <button type="button" class="btn btn-primary">Save</button>
                                            </div>
                                            <div class="btn-group-vertical">
                                                <button type="button" class="btn btn-primary">Remove</button>
                                            </div>
                                            <div class="btn-group-vertical">
                                                <c:if test="${cookbook.visibility != 'PUBLIC'}">
                                                    <button type="button" class="btn btn-primary">Make public</button>
                                                </c:if>
                                                <c:if test="${cookbook.visibility != 'PRIVATE'}">
                                                    <button type="button" class="btn btn-primary">Make private</button>
                                                </c:if>
                                                <c:if test="${cookbook.visibility != 'FRIENDS'}">
                                                    <button type="button" class="btn btn-primary">Make shared</button>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
