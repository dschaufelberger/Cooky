<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 27.11.2015
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-8">
    <div class="alert alert-info">
        <h2>Account settings</h2>
        <%--<h4>${user.username}</h4>--%>

        <p>
            As a registered Cooky User you are able to edit your password on this page.
        </p>
    </div>
</div>
<div class="container">
    <section style="padding-bottom: 50px; padding-top: 50px;">
        <div class="row">
            <form:form class="validatedForm" method="POST" action="validatePassword" commandName="password">
                <div class="form-group col-md-8">
                    <h3>Change Your Password</h3>
                    <br />
                    <form:label path="oldpassword">Enter Old Password</form:label>
                    <form:password path="oldpassword" class="form-control" id="oldpassword"/>
                    <form:errors path="oldpassword" cssClass="cooky-formError" element="div class=\"col-sm-10\""  />
                    <%--<input id="oldpassword" name="oldpassword" type="password" class="form-control" required="required">--%>
                    <form:label path="newpassword">Enter New Password</form:label>
                    <form:password path="newpassword" class="form-control" id="newpassword"/>
                    <form:errors path="newpassword" cssClass="cooky-formError" element="div class=\"col-sm-10\""  />
                    <form:label path="password_confirm">Confirm New Password</form:label>
                    <form:password path="password_confirm" class="form-control" id="password_confirm"/>
                    <form:errors path="password_confirm" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
                    <br>
                    <input type="submit" class="btn btn-warning" value="Change Password">
                </div>
            </form:form>
        </div>
        <!-- ROW END -->


    </section>
    <!-- SECTION END -->
</div>
<!-- CONATINER END -->
