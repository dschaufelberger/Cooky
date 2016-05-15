package de.cookyapp.persistence.repositories.app;

import de.cookyapp.persistence.entities.UserEntity;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
public interface IUserCrudRepository extends IBaseCrudRepository<UserEntity, Integer> {
    UserEntity findByUsername( String username );
}
