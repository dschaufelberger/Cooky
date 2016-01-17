<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<form id="loginForm" class="form-signin" action="/login" method="post" role="form">

    <label for="username" class="sr-only">Benutzername:</label>
    <input id="username" name="username" type="text" value="" class="form-control" placeholder="Benutzername">

    <label for="password" class="sr-only">Passwort:</label>
    <input id="password" name="password" type="password" value="" class="form-control" placeholder="Passwort">

    <button type="submit" class="btn btn-success btn-primary btn-block">Anmelden</button>

    <div>
        <sec:csrfInput />
    </div>
</form>