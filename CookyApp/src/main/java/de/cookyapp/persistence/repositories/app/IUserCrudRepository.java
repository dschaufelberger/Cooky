package de.cookyapp.persistence.repositories.app;

import java.util.List;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.UserEntity;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
public interface IUserCrudRepository extends IBaseCrudRepository<UserEntity, Integer> {
    UserEntity findByUsername( String username );

    List<UserEntity> findByUsernameContaining( String username );
}
