<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 28.05.2016
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ attribute name="rating" type="java.lang.Integer" required="true" %>
<%@ attribute name="maxRating" type="java.lang.Integer" required="true" %>

<span>
    <c:forEach begin="1" end="${rating}">
        <span class="glyphicon glyphicon-star cooky-recipeRating"></span>
    </c:forEach>
    <c:forEach begin="${rating + 1}" end="${maxRating}">
        <span class="glyphicon glyphicon-star-empty cooky-recipeRating"></span>
    </c:forEach>
</span>
