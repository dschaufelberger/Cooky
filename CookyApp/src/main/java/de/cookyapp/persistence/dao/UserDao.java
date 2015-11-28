package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.entities.UserEntity;

/**
 * Created by Dominik Schaufelberger on 28.11.2015.
 */
public class UserDao extends GenericCookyDaoImplementation<UserEntity, Integer> {

    public UserDao() {
        super(UserEntity.class);
    }
}
