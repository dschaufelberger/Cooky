<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 02.12.2015
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hibernate Mappings Test</title>
</head>
<body>
<%--Users:--%>
<%--<ul>--%>
<%--<c:forEach items="${userList}" var="user">--%>
<%--<li>--%>
<%--<c:out value="${user.username}" />--%>
<%--<ul>--%>
<%--<li><c:out value="${user.forename}" /></li>--%>
<%--<li><c:out value="${user.surname}" /></li>--%>
<%--<li><c:out value="${user.email}" /></li>--%>
<%--<li><c:out value="${user.address.street} ${user.address.houseNumber} ${user.address.city}" />--%>
<%--</li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
<%--<hr>--%>
Recipes:
<ul>
    <c:forEach items="${recipeList}" var="recipe">
        <li>
            <c:out value="${recipe.name}" />
            <br>
            <c:out value="${recipe.shortDescription}" />
            <br>
            Zutaten:
            <br>
            <ul>
                <c:forEach items="${recipe.ingredients}" var="ingredient">
                    <li>
                        <c:out value="${ingredient.amount} ${ingredient.unit} ${ingredient.ingredient.name}" />
                    </li>
                </c:forEach>
            </ul>
        </li>
    </c:forEach>
</ul>
<%--<hr>--%>
<%--Addresses:--%>
<%--<ul>--%>
<%--<c:forEach items="${addressList}" var="address">--%>
<%--<li>--%>
<%--<c:out value="${address.street}" />--%>
<%--<ul>--%>
<%--<li><c:out value="${address.city}" /></li>--%>
<%--<li><c:out value="${address.houseNumber}" /></li>--%>
<%--<li><c:out value="${address.postcode}" /></li>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
</body>
</html>
