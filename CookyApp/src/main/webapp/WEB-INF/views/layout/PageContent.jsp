<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 14.05.2016
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<div class="container-fluid cooky-fill">
    <div class="row cooky-fill">
        <div class="col-md-2"></div>
        <div class="col-md-8 cooky-mainContent cooky-fill">
            <tiles:insertAttribute name="pagecontent" />
        </div>
        <div class="col-md-2"></div>
    </div>
</div>