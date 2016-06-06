<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cooky" uri="http://cookyapp.de/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Cooky</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/recipes">Recipes</a>
                </li>
                <sec:authorize access="isAuthenticated()" var="userIsAuthenticated">
                    <li>
                        <a href="/cookbooks/manage">Cookbooks</a>
                    </li>
                    <li>
                        <a href="/cookys">Cookys</a>
                    </li>
                </sec:authorize>
            </ul>

            <form:form action="/search" method="post" cssClass="navbar-form navbar-left" commandName="search">
                <cooky:search />
            </form:form>

            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="${userIsAuthenticated}">
                    <li id="friendRequests" class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">
                            <span class="fa fa-users" aria-hidden="true">
                                <span class="badge"><c:if test="${friendRequests.size() > 0}"
                                                          var="hasFriendRequests">${friendRequests.size()}</c:if></span>
                            </span>
                        </a>
                        <ul class="dropdown-menu friend-request-dropdown">
                            <li class="friend-request-label">
                                <c:if test="${hasFriendRequests}">New friend requests:</c:if>
                                <c:if test="${not hasFriendRequests}">No pending friend requests.</c:if>
                            </li>
                            <c:forEach var="friendRequest" items="${friendRequests}">
                                <li>
                                    <cooky:friends request="${friendRequest}" />
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </sec:authorize>
                <li class="dropdown">
                    <%-- If the user is authorized render "Hello 'Username'" and set the icon to a user icon. --%>
                    <sec:authorize access="${userIsAuthenticated}">
                        <a class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-user"></span>
                            Hello <sec:authentication property="principal.username" />
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="/account/details">
                                    <span class="glyphicon glyphicon-cog"></span>
                                    Manage Account
                                </a>
                            </li>
                            <li>
                                <a onclick="$('form#logOutForm').submit();">
                                    <form:form id="logOutForm" method="post" action="/logout">
                                    </form:form>
                                    <span class="glyphicon glyphicon-log-out"></span>
                                    Logout
                                </a>
                                    <%--"--%>
                                    <%----%>
                            </li>
                        </ul>
                    </sec:authorize>

                    <%-- Else render "Login" and a login icon. --%>
                    <sec:authorize access="${not userIsAuthenticated}">
                        <a class="dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-log-in"></span>
                            Login
                        </a>

                        <div class="dropdown-menu cooky-navigation-dropdown">
                            <cooky:login />
                        </div>
                    </sec:authorize>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
