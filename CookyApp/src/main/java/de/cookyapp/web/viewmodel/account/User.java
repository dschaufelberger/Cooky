package de.cookyapp.web.viewmodel.account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.cookyapp.enums.Gender;
import de.cookyapp.persistence.entities.UserEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Mario on 30.11.2015.
 */
public class User {


    @NotBlank( message = "Bitte geben Sie Ihren Namen an." )
    @Size( max = 30, message = "Der Name darf nur aus 30 Zeichen bestehen." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß]+(-?[a-zA-ZäöüÄÖÜß]*)*$", message = "Der Name darf nur aus Klein- und Großbuchstaben und einem Bindestrich bestehen." )
    private String forename;

    @NotBlank( message = "Bitte geben Sie Ihren Namen an." )
    @Size( max = 30, message = "Der Name darf nur aus maximal 30 Zeichen bestehen." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß]+(-?[a-zA-ZäöüÄÖÜß]*)*$", message = "Der Name darf nur aus Klein- und Großbuchstaben und einem Bindestrich bestehen." )
    private String surname;

    @Email
    @Size( max = 50, message = "Die Email darf nur aus maximal 50 Zeichen bestehen." )
    private String email;


    private String username;
    private Gender gender;
    private LocalDate birthdate;
    private LocalDateTime registrationDate;


    private int id;

    public User() {

    }

    public User( UserEntity usere ) {
        this.forename = usere.getForename();
        this.surname = usere.getSurname();
        this.email = usere.getEmail();
        this.id = usere.getId();
        this.gender = usere.getGender();
        this.birthdate = usere.getBirthdate();
        this.registrationDate = usere.getRegistrationDate();
        this.username = usere.getUsername();

    }


    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate( LocalDateTime registrationDate ) {
        this.registrationDate = registrationDate;
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