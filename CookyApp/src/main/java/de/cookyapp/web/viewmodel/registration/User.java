package de.cookyapp.web.viewmodel.registration;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.cookyapp.enums.Gender;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
public class User {
    @NotBlank( message = "The username must have a length between 8 and 30 characters." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß][a-zA-ZäöüÄÖÜß0-9_-]*$", message = "The username must begin with a letter, can contain letters and numbers and the following special characters: \"-_\"." )
    @Size( min = 5, max = 30, message = "The username must have a length between 8 and 30 characters." )
    private String username;

    @NotBlank( message = "The password must have a length between 8 and 32 characters." )
    @Size( min = 8, max = 32, message = "The password must have a length between 8 and 32 characters." )
    @Pattern( regexp = "^[a-zA-Z0-9äöüÄÖÜß\\-_!#\\?\\(\\)\\$%&\\+\\*]*$", message = "The password can only contain letters, numbers and the following special characters: \"-_!#?()$%&+*\"." )
    private String password;

    @NotBlank( message = "The password must have a length between 8 and 32 characters." )
    @Size( min = 8, max = 32, message = "The password must have a length between 8 and 32 characters." )
    @Pattern( regexp = "^[a-zA-Z0-9äöüÄÖÜß\\-_!#\\?\\(\\)\\$%&\\+\\*]*$", message = "The password can only contain letters, numbers and the following special characters: \"-_!#?()$%&+*\"." )
    private String repeatedPassword;

    @NotBlank( message = "Please enter your forename." )
    @Size( max = 30, message = "The forename can only contain a maximum of 30 characters." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß]+(-?[a-zA-ZäöüÄÖÜß]*)*$", message = "The forename can only contain letters and hyphens and must begin with a letter." )
    private String forename;

    @NotBlank( message = "Please enter your surname.\"" )
    @Size( max = 30, message = "The surname can only contain a maximum of 30 characters." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß]+(-?[a-zA-ZäöüÄÖÜß]*)*$", message = "The surname can only contain letters and hyphens and must begin with a letter." )
    private String surname;

    @Email
    @Size( max = 50, message = "The email can only contain a maximum of 50 characters." )
    private String email;

    @NotNull( message = "Please select one of the values." )
    private Gender gender;

    @NotNull( message = "Please enter a valid date in the yyyy-mm-dd format." )
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private LocalDate birthdate;

    @Valid
    private Address address;

    public User() {
    }

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


    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword( String repeatedPassword ) {
        this.repeatedPassword = repeatedPassword;
    }

    public String getForename() {
        return forename;
    }

    public void setForename( String forename ) {
        this.forename = forename;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender( Gender gender ) {
        this.gender = gender;
    }


    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate( LocalDate birthdate ) {
        this.birthdate = birthdate;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress( Address address ) {
        this.address = address;
    }


    public Gender[] getAvailableGenders() {
        return Gender.values();
    }


}
