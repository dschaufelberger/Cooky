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

<table class="table">
    <tbody>
    <tr>
        <td>
            <form action="/matchCenter/categoriesOverview" method="post">
                <button type="submit" class="btn btn-default btn-block">Add preferences to get more matches!</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/matchCenter/userPreferences" method="post">
                <button type="submit" class="btn btn-default btn-block">My Preferences</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </td>
    </tr>
    <tr>
        <td>
            <form action="/matchCenter/matches" method="post">
                <button type="submit" class="btn btn-default btn-block">Get Cooking matches!</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
        </td>
    </tr>
    </tbody>
</table>
