<%--
  Created by IntelliJ IDEA.
  User: Dominik Schaufelberger
  Date: 27.11.2015
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="POST" action="/registration" commandName="user" cssClass="form-horizontal" role="form">


    <%-- General user information --%>
    <div class="form-group">
        <form:label path="forename" cssClass="control-label col-sm-2">Forename</form:label>
        <div class="col-sm-10">
            <form:input path="forename" cssClass="form-control" />
            <form:errors path="forename" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="surname" cssClass="control-label col-sm-2">Surname</form:label>
        <div class="col-sm-10">
            <form:input path="surname" cssClass="form-control" />
            <form:errors path="surname" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="email" cssClass="control-label col-sm-2">Email</form:label>
        <div class="col-sm-10">
            <form:input path="email" type="email" cssClass="form-control" element="div class=\"col-sm-10\"" />
            <form:errors path="email" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="gender" cssClass="control-label col-sm-2">Gender</form:label>
        <div class="col-sm-10">
            <form:select path="gender" cssClass="form-control">
                <form:option value="" label="Please select.." />
                <form:options items="${availableGenders}" itemLabel="addressForm" element="div class=\"col-sm-10\"" />
            </form:select>
            <form:errors path="gender" cssClass="cooky-formError" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="birthdate" cssClass="control-label col-sm-2">Date of birth</form:label>
        <div class="col-sm-10">
            <form:input path="birthdate" cssClass="form-control" />
            <form:errors path="birthdate" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>


    <%-- Address related information --%>
    <div class="form-group">
        <form:label path="address.street" cssClass="control-label col-sm-2">Street</form:label>
        <div class="col-sm-10">
            <form:input path="address.street" cssClass="form-control" />
            <form:errors path="address.street" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="address.houseNumber" cssClass="control-label col-sm-2">House number</form:label>
        <div class="col-sm-10">
            <form:input path="address.houseNumber" cssClass="form-control" />
            <form:errors path="address.houseNumber" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="address.city" cssClass="control-label col-sm-2">City</form:label>
        <div class="col-sm-10">
            <form:input path="address.city" cssClass="form-control" />
            <form:errors path="address.city" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="address.postcode" cssClass="control-label col-sm-2">Postcode</form:label>
        <div class="col-sm-10">
            <form:input path="address.postcode" cssClass="form-control" />
            <form:errors path="address.postcode" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>


    <%-- Username and password --%>
    <br>
    <div class="form-group">
        <form:label path="username" cssClass="control-label col-sm-2">Username</form:label>
        <div class="col-sm-10">
            <form:input path="username" cssClass="form-control" />
            <form:errors path="username" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="password" cssClass="control-label col-sm-2">Password</form:label>
        <div class="col-sm-10">
            <form:password path="password" cssClass="form-control" />
            <form:errors path="password" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>
    <div class="form-group">
        <form:label path="repeatedPassword" cssClass="control-label col-sm-2">Repeat Password</form:label>
        <div class="col-sm-10">
            <form:password path="repeatedPassword" cssClass="form-control" />
            <form:errors path="repeatedPassword" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Register</button>
        </div>
    </div>
</form:form>

<script>
    $(function () {
        $('#birthdate').datepicker({
            maxDate: '-12Y',
            changeMonth: true,
            changeYear: true,
            dateForm: 'yy-mm-dd'
        });
        $('#birthdate').datepicker('option', 'dateFormat', 'yy-mm-dd');
    });
</script>
