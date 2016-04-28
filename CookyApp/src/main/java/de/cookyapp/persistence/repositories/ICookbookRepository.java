package de.cookyapp.persistence.repositories;

import java.util.Collection;

import de.cookyapp.enums.CookbookVisibility;
import de.cookyapp.persistence.entities.CookbookEntity;

/**
 * Created by Dominik Schaufelberger on 21.04.2016.
 */
public interface ICookbookRepository extends IBaseCrudRepository<CookbookEntity, Integer> {
    Collection<CookbookEntity> findByOwnerId( int ownerId );

    Collection<CookbookEntity> findByVisibility( CookbookVisibility visibility );

    Collection<CookbookEntity> findByOwnerIdAndVisibility( int ownerId, CookbookVisibility visibility );
}
