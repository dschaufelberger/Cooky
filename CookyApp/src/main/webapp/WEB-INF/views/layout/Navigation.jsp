<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Cooky</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <%-- If the user is authorized render "Hallo 'Username'" and set the icon to a user icon. --%>
                    <sec:authorize access="isAuthenticated()">
                        <a class="dropdown-toggle glyphicon glyphicon-user" data-toggle="dropdown">
                            Hallo <sec:authentication property="principal.username" />
                        </a>
                    </sec:authorize>

                    <%-- Else render "Login" and a login icon. --%>
                    <sec:authorize access="not isAuthenticated()">
                        <a class="dropdown-toggle glyphicon glyphicon-log-in" data-toggle="dropdown">
                            Login
                        </a>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                        <ul class="dropdown-menu">
                            <li>
                                <form:form id="logOutForm" method="post" action="/logout">
                                    <a class="glyphicon glyphicon-log-out" href="#" onclick="$('form#logoutForm').submit();">Logout</a>
                                </form:form>
                            </li>
                        </ul>

                    </sec:authorize>
                    <sec:authorize access="not isAuthenticated()">
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
