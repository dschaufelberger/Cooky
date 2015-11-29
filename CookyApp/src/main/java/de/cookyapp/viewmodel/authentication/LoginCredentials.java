package de.cookyapp.viewmodel.authentication;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Dominik Schaufelberger on 29.11.2015.
 */

public class LoginCredentials {
    @NotBlank(message = "Bitte geben Sie Ihren Benutzernamen ein.")
    private String j_username;

    @NotBlank(message = "Bitte geben Sie Ihren Benutzernamen ein.")
    private String j_password;

    public String getJ_username() {
        return j_username;
    }

    public void setJ_username( String j_username ) {
        this.j_username = j_username;
    }

    public String getJ_password() {
        return j_password;
    }

    public void setJ_password( String j_password ) {
        this.j_password = j_password;
    }
}
