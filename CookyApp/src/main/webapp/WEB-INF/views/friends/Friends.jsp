<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 05.06.2016
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>Your Cookys</h1>
<p class="cooky-description-text">View the profiles of your friends and manage them</p>

<c:if test="${friendships.size() > 0}" var="hasFriendships">
    <table class="table friends-table">
        <tbody>

        <c:forEach var="friendship" items="${friendships}">
            <tr>
                <td>
                    <span>${friendship.friendname}</span>
                </td>
                <td>
                    <span class="btn btn-default">View Profile</span>
                </td>
                <td>
                    <form action="/cookys/remove" method="post">
                        <input type="hidden" name="friend" value="${friendship.friendId}">
                        <input type="hidden" name="me" value="${friendship.myId}">
                        <sec:csrfInput />
                        <button type="submit" class="btn btn-default">Unfriend</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${not hasFriendships}">
    <span class="alert alert-info">You currently do not have Cookys <span class="fa fa-frown-o"></span>.</span>
</c:if>

<p>Your pending friend requests:</p>
<c:if test="${pendingRequests.size() > 0}" var="hasPendingRequests">
    <table class="table friends-table pending-table">
        <tbody>

        <c:forEach var="request" items="${pendingRequests}">
            <tr>
                <td>
                    <span>${request.friendname}</span>
                </td>
                <td>
                    <span class="btn btn-default">View Profile</span>
                </td>
                <td>
                    ...request pending
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${not hasPendingRequests}">
    <span class="alert alert-info">You have no pending friend requests.</span>
</c:if>
