<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form id="loginForm" action="/login" method="post" role="form">
    <div class="form-group">
        <label for="username">Benutzername:</label>
        <input id="username" name="username" type="text" value="">
    </div>
    <div class="form-group">
        <label for="password">Passwort:</label>
        <input id="password" name="password" type="password" value="">
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-default">Anmelden</button>
    </div>
    <div>
        <sec:csrfInput />
    </div>
</form>