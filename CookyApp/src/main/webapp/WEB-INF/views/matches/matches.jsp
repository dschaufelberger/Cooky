<%--
  Created by IntelliJ IDEA.
  User: Jasper
  Date: 05.06.2016
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:forEach var="user" items="${usersList}" varStatus="loop">
    <c:if test="${loop.index % 3 == 0}">
        <div class="row">
    </c:if>

    <div class="col-md-4">
        <div class="thumbnail size">
            <div class="caption">
                <!-- TODO: add link to user profile -->
                <a><h3>${user.username}</h3></a>
            </div>
        </div>
    </div>

    <c:if test="${loop.index % 3 == 2}">
        </div>
    </c:if>
</c:forEach>
