<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 04.06.2016
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3>Your Preferences</h3>
<ul class="col-md-6 list-group">
    <c:forEach var="preferences" items="${userPreferences}">
        <form:form method="POST" action="/matchCenter/remove" commandName="preferences">
            <li class="list-group-item">
                <span>${preferences.categoryName}</span>
                <button type="submit" class="btn btn-xs btn-default pull-right">
                    <span class="glyphicon glyphicon-remove pull-right"> </span>
                </button>
                <input type="hidden" value="${preferences.id}" name="id">
            </li>
        </form:form>
    </c:forEach>
</ul>
