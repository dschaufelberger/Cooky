<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 22.04.2016
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:forEach var="cookbook" items="${cookbooks}">
    <div class="container">
        <div class="panel-group">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" href="#collapse${cookbook.id}">${cookbook.name}</a>
                    </h4>
                </div>
                <div id="collapse${cookbook.id}" class="panel-collapse collapse">
                    <ul class="list-group">
                        <c:forEach var="recipeVar" items="${cookbook.recipes}">
                            <li class="list-group-item">${recipeVar.name}</li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
