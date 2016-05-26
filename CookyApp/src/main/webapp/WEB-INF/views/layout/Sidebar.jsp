<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 26.05.2016
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="cooky-sidebar">
    <div class="sidebar-rotmpic">
        <img src="http://keenthemes.com/preview/metronic/theme/assets/admin/pages/media/profile/profile_user.jpg"
             class="img-responsive" alt="">
    </div>
    <div class="sidebar-title">
        <div class="sidebar-recipe-name">
            ${recipeOfTheMonth.name}
        </div>
        <div class="sidebar-recipe-shortdesc">
            ${recipe.shortDescription}
        </div>
    </div>
    <p align="center">
        <a href="/recipes/view/${recipeOfTheMonth.id}" class="btn btn-primary btn-block">Open</a>
    </p>
</div>



