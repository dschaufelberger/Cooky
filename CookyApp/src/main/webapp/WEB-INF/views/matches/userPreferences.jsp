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

<form:form method="POST" action="cookingMatches/userPreferences" commandName="preferences">
    <c:forEach var="preferences" items="${userPreferences}">
        <div>
            <span>${preferences.categoryName}</span>
            <span id="${preferences.id}" class="glyphicon glyphicon-remove" onclick="removePreference(this.id)"> </span>
        </div>
    </c:forEach>
</form:form>

<script>
    function removePreference(id) {
        var settings = {
            method: 'POST',
            url: '/matchCenter/remove/' + id,
            data: {
                id: id,
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
            }
        }
        $.ajax(settings);
    }
</script>
