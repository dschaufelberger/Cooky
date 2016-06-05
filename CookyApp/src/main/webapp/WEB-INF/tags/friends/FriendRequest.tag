<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="request" type="de.cookyapp.web.viewmodel.friends.FriendRequest" required="true" %>

<div class="cooky-friend-request">
    <%-- TODO provide user profile link --%>
    <%-- <a href="#">${request.inquirerUsername}</a> --%>

    <span>${request.inquirerUsername}</span>

    <form action="<spring:url value="/cookys/accept"/>" method="post">
        <input type="hidden" name="from" value="${request.inquirerId}">
        <input type="hidden" name="to" value="${request.requestedId}">
        <button type="submit">
            <span class="glyphicon glyphicon-ok friend-request-accept"></span>
        </button>
    </form>

    <form action="<spring:url value="/cookys/reject"/>" method="post">
        <input type="hidden" name="from" value="${request.inquirerId}">
        <input type="hidden" name="to" value="${request.requestedId}">
        <button type="submit">
            <span class="glyphicon glyphicon-remove friend-request-reject"></span>
        </button>
    </form>
</div>
