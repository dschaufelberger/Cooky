package de.cookyapp.viewmodel.registration;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import de.cookyapp.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
public class User {
    @NotBlank( message = "Der Benutzername muss zwischen 8 und 30 Zeichen lang sein." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß][a-zA-ZäöüÄÖÜß0-9_-]*$", message = "Der Benutzername muss mit einem Buchstaben beginnen und darf neben Buchstaben und Zahlen folgende Sonderzeichen enthalten: \"-_\"." )
    @Size( min = 5, max = 30, message = "Der Benutzername muss zwischen 8 und 30 Zeichen lang sein." )
    private String username;

    @NotBlank(message = "Das Passwort muss zwischen 8 und 32 Zeichen lang sein.")
    @Size( min = 8, max = 32, message = "Das Passwort muss zwischen 8 und 32 Zeichen lang sein." )
    @Pattern( regexp = "^[a-zA-Z0-9äöüÄÖÜß\\-_!#\\?\\(\\)\\$%&\\+\\*]*$", message = "Das Passwort darf nur aus Klein- und Großbuchstaben, Zahlen und folgenden Sonderzeichen bestehen: \"-_!#?()$%&+*\"." )
    private String password;

    @NotBlank(message = "Bitte geben Sie Ihren Namen an.")
    @Size( max = 30, message = "Der Name darf nur aus 30 Zeichen bestehen." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß]+(-?[a-zA-ZäöüÄÖÜß]*)*$", message = "Der Name darf nur aus Klein- und Großbuchstaben und einem Bindestrich bestehen." )
    private String forename;

    @NotBlank(message = "Bitte geben Sie Ihren Namen an.")
    @Size( max = 30, message = "Der Name darf nur aus maximal 30 Zeichen bestehen." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß]+(-?[a-zA-ZäöüÄÖÜß]*)*$", message = "Der Name darf nur aus Klein- und Großbuchstaben und einem Bindestrich bestehen." )
    private String surname;

    @Email
    @Size( max = 50, message = "Die Email darf nur aus maximal 50 Zeichen bestehen." )
    private String email;

    @NotNull(message = "Bitte wählen Sie einen der vorgegebenen Werte.")
    private Gender gender;

    @NotNull(message = "Bitte geben Sie ein gültiges Datum an.")
    //@Past(message = "Das Geburtstdatum muss in der Vergangeheit liegen.")
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private LocalDate birthdate;

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


    public Gender[] getAvailableGenders() {
        return Gender.values();
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        return sb.toString();
    }
}
