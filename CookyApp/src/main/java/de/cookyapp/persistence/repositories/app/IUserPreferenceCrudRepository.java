package de.cookyapp.persistence.repositories.app;

import de.cookyapp.persistence.entities.UserPreferenceEntity;

import java.util.List;

/**
 * Created by Jasper on 01.06.2016.
 */
public interface IUserPreferenceCrudRepository extends IBaseCrudRepository<UserPreferenceEntity, Integer> {
    List<UserPreferenceEntity> findByCategoryName ( String category );

    List<UserPreferenceEntity> findByUserId ( int id );
}
