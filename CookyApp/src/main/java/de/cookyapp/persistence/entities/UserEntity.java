package de.cookyapp.persistence.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import de.cookyapp.enums.AccountState;
import de.cookyapp.enums.Gender;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "User", schema = "Cooky_Dev" )
public class UserEntity {
    private int id;
    private String username;
    private String password;
    private String forename;
    private String surname;
    private String email;
    private Gender gender;
    private LocalDate birthdate;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLoginDate;
    private AccountState accountState;

    @Id
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "Username", nullable = false, length = 30 )
    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    @Basic
    @Column( name = "Password", nullable = false, length = 32 )
    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    @Basic
    @Column( name = "Forename", nullable = false, length = 30 )
    public String getForename() {
        return forename;
    }

    public void setForename( String forename ) {
        this.forename = forename;
    }

    @Basic
    @Column( name = "Surname", nullable = false, length = 30 )
    public String getSurname() {
        return surname;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }

    @Basic
    @Column( name = "Email", nullable = false, length = 50 )
    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Basic
    @Enumerated( EnumType.STRING )
    @Column( name = "Gender", nullable = true, length = 10 )
    public Gender getGender() {
        return gender;
    }

    public void setGender( Gender gender ) {
        this.gender = gender;
    }

    @Basic
    @Column( name = "Birthdate", nullable = true )
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate( LocalDate birthdate ) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column( name = "RegistrationDate", nullable = true )
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate( LocalDateTime registrationDate ) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column( name = "LastLoginDate", nullable = true )
    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate( LocalDateTime lastLoginDate ) {
        this.lastLoginDate = lastLoginDate;
    }

    @Basic
    @Enumerated( EnumType.STRING )
    @Column( name = "AccountState", nullable = true, length = 10 )
    public AccountState getAccountState() {
        return accountState;
    }

    public void setAccountState( AccountState accountState ) {
        this.accountState = accountState;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        UserEntity that = (UserEntity) o;

        if ( id != that.id )
            return false;
        if ( username != null ? !username.equals( that.username ) : that.username != null )
            return false;
        if ( password != null ? !password.equals( that.password ) : that.password != null )
            return false;
        if ( forename != null ? !forename.equals( that.forename ) : that.forename != null )
            return false;
        if ( surname != null ? !surname.equals( that.surname ) : that.surname != null )
            return false;
        if ( email != null ? !email.equals( that.email ) : that.email != null )
            return false;
        if ( gender != null ? !gender.equals( that.gender ) : that.gender != null )
            return false;
        if ( birthdate != null ? !birthdate.equals( that.birthdate ) : that.birthdate != null )
            return false;
        if ( registrationDate != null ? !registrationDate.equals( that.registrationDate ) : that.registrationDate != null )
            return false;
        if ( lastLoginDate != null ? !lastLoginDate.equals( that.lastLoginDate ) : that.lastLoginDate != null )
            return false;
        if ( accountState != null ? !accountState.equals( that.accountState ) : that.accountState != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (forename != null ? forename.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (lastLoginDate != null ? lastLoginDate.hashCode() : 0);
        result = 31 * result + (accountState != null ? accountState.hashCode() : 0);
        return result;
    }
}
