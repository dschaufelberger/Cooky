package de.cookyapp.service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import de.cookyapp.enums.AccountState;
import de.cookyapp.enums.Gender;
import de.cookyapp.persistence.entities.UserEntity;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
public class User {
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

    public User() {
    }

    public User( UserEntity userEntity ) {
        setId( userEntity.getId() );
        setUsername( userEntity.getUsername() );
        setPassword( userEntity.getPassword() );
        setForename( userEntity.getForename() );
        setSurname( userEntity.getSurname() );
        setEmail( userEntity.getEmail() );
        setGender( userEntity.getGender() );
        setBirthdate( userEntity.getBirthdate() );
        setRegistrationDate( userEntity.getRegistrationDate() );
        setLastLoginDate( userEntity.getLastLoginDate() );
        setAccountState( userEntity.getAccountState() );
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
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


    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate( LocalDateTime registrationDate ) {
        this.registrationDate = registrationDate;
    }


    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate( LocalDateTime lastLoginDate ) {
        this.lastLoginDate = lastLoginDate;
    }


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

        User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
