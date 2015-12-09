package de.cookyapp.viewmodel.account;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.cookyapp.enums.Gender;
import de.cookyapp.persistence.entities.UserEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Mario on 30.11.2015.
 */
public class Password {


    @NotBlank( message = "Das Passwort muss zwischen 8 und 32 Zeichen lang sein." )
    @Size( min = 8, max = 32, message = "Das Passwort muss zwischen 8 und 32 Zeichen lang sein." )
    @Pattern( regexp = "^[a-zA-Z0-9äöüÄÖÜß\\-_!#\\?\\(\\)\\$%&\\+\\*]*$", message = "Das Passwort darf nur aus Klein- und Großbuchstaben, Zahlen und folgenden Sonderzeichen bestehen: \"-_!#?()$%&+*\"." )
    private String newpassword;

    @NotBlank( message = "Bitte geben Sie Ihr aktuelles Passwort ein!" )
    private String oldpassword;

    private String password_confirm;

    public Password() {

    }


    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword( String password ) {
        this.oldpassword = password;
    }


    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword( String newpassword ) {
        this.newpassword = newpassword;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm( String password_confirm ) {
        this.password_confirm = password_confirm;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        return sb.toString();
    }
}



