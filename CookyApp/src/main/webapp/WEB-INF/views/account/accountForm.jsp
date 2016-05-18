<%--
  Created by IntelliJ IDEA.
  User: Mario
  Date: 27.11.2015
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <div class="row">
        <form:form method="POST" action="/account/edit" commandName="user">
            <div class="col-md-4">
                <img src="/resources/images/profilepicture.png" class="img-rounded img-responsive" />
                <br />
                <br />
                <form:label path="username">Username</form:label>
                <form:input path="username" class="form-control" readonly="true" />
                <form:errors path="forename" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
                <form:label path="forename">Forename</form:label>
                <form:input path="forename" class="form-control" />
                <form:errors path="forename" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
                <form:label path="surname">Surname</form:label>
                <form:input path="surname" class="form-control" />
                <form:errors path="surname" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
                <form:label path="email">Email</form:label>
                <form:input path="email" type="email" class="form-control" />
                <form:errors path="email" cssClass="cooky-formError" element="div class=\"col-sm-10\"" />
                <label>Gender</label>
                <input type="text" class="form-control" value="${user.gender}" disabled="disabled">
                <label>Birthdate</label>
                <input type="text" class="form-control" value="${user.birthdate}" disabled="disabled">
                <label>Registration Date</label>
                <input type="text" class="form-control" value="${user.registrationDate}" disabled="disabled">
                <br>
                <!--<a href="#" class="btn btn-success">Update Details</a>-->
                <form:hidden path="id" />
                    <%--<input type="hidden" name="id" value="${user.id}">--%>
                <input type="submit" class="btn btn-success" value="Update Account">

                <br /><br />
            </div>
        </form:form>
        <div class="col-md-8">
            <div class="alert alert-info">
                <h2>Account settings</h2>
                <h4>${user.username}</h4>

                <p>
                    As a registered Cooky User you are able to edit your profile data and settings on this page.
                </p>
            </div>


            <form method="post" action="changePassword">
                <sec:csrfInput />
                <div class="form-group col-md-8">
                    <input type="submit" class="btn btn-warning" value="Change Password">
                </div>
            </form>

        </div>
    </div>
    <!-- ROW END -->


</section>
<!-- SECTION END -->
