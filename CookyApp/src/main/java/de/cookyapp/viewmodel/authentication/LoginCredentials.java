package de.cookyapp.viewmodel.authentication;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Dominik Schaufelberger on 29.11.2015.
 */

public class LoginCredentials {
    @NotBlank(message = "Bitte geben Sie Ihren Benutzernamen ein.")
    private String username;

    @NotBlank(message = "Bitte geben Sie Ihren Benutzernamen ein.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
