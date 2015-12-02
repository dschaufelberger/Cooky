package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.entities.UserEntity;

/**
 * This class inherits from the GenericCookyDaoImplementation abstract class.
 * Since there is a basic implementation for all the CRUD operations in the abstract super class, just the super construtor
 * needs to be called with the entity class to persist as parameter.
 *
 * Created by Dominik Schaufelberger on 28.11.2015.
 */
public class UserDao extends GenericCookyDaoImplementation<UserEntity, Integer> {

    public UserDao() {
        super(UserEntity.class);
    }

    @Override
    protected void loadLazy(UserEntity persistentObject) {

    }
}
