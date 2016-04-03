package de.cookyapp.service.dto;

import de.cookyapp.persistence.entities.UserEntity;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
public class User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public User() {
    }

    public User( UserEntity userEntity ) {
        setUsername( userEntity.getUsername() );
    }
}
