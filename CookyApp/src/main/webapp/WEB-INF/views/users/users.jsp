<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 29.05.2016
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${usersList.size() == 0}">
    <span class="alert alert-info search-no-result">No Cookys were found, that matched the entered name.</span>
</c:if>

<c:forEach var="user" items="${usersList}" varStatus="loop">
    <c:if test="${loop.index % 3 == 0}">
        <div class="row">
    </c:if>

    <div class="col-md-4">
        <div class="thumbnail size">
            <div class="caption">
                <!-- TODO: add link to user profile -->
                <a><h3>${user.username}</h3></a>
                <form action="/cookys/add" method="post">
                    <input type="hidden" name="requested">
                    <sec:csrfInput/>
                    <button type="submit" class="btn btn-default">
                        Send Friend Request
                    </button>
                </form>
            </div>
        </div>
    </div>

    <c:if test="${(loop.index % 3 == 2) || loop.last}">
        </div>
    </c:if>
</c:forEach>