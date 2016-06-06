package de.cookyapp.persistence.repositories.app;

import java.util.List;

import de.cookyapp.persistence.entities.UserPreferenceEntity;

/**
 * Created by Jasper on 01.06.2016.
 */
public interface IUserPreferenceCrudRepository extends IBaseCrudRepository<UserPreferenceEntity, Integer> {
    List<UserPreferenceEntity> findByCategoryName( String category );

    List<UserPreferenceEntity> findByCategoryNameAndUserId( String category, int id );

    List<UserPreferenceEntity> findByCategoryNameIn( List<String> categories );

    List<UserPreferenceEntity> findByUserId( int id );
}
