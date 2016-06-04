<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 04.06.2016
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<table>
    <ul class="list-group">
        <li class="list-group-item">
            <form action="/matchCenter/categoriesOverview" method="post">
                <button type="submit" class="btn btn-default">Add preferences to get more matches!</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
        <li class="list-group-item">
            <form action="/matchCenter/userPreferences" method="post">
                <button type="submit" class="btn btn-default">My Preferences</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
        <li class="list-group-item">
            <form action="/matchCenter/match" method="post">
                <button type="submit" class="btn btn-default">Get Cooking matches!</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
    </ul>
</table>
