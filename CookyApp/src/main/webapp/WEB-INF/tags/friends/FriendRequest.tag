<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="request" type="de.cookyapp.web.viewmodel.friends.FriendRequest" required="true" %>

<div class="cooky-friend-request">
    <%-- TODO provide user profile link --%>
    <%-- <a href="#">${request.inquirerUsername}</a> --%>

    <span>${request.inquirerUsername}</span>
    <input type="hidden" class="request-inquirer" value="${request.inquirerId}">
    <input type="hidden" class="request-requested" value="${request.requestedId}">
    <span class="btn">
        <a class="glyphicon glyphicon-ok friend-request-accept"></a>
    </span>
    <span class="btn">
        <a class="glyphicon glyphicon-remove friend-request-reject"></a>
    </span>
</div>
