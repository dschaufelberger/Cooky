package de.cookyapp.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 * This is a generic interface for a DAO in Cooky.
 *
 * Created by Dominik Schaufelberger on 28.11.2015.
 */
public interface GenericCookyDAO <T, PK extends Serializable> {
    /**
     * Saves/Persists an object to the database and returns the generated primary key.
     *
     * @param transientObject The object to persist.
     * @return The persisted objects primary key.
     */
    PK save( T transientObject );

    /**
     * Loads a persistent object from the database identified by the primary key paramter.
     *
     * @param id The primary key for the database schema.
     * @return The persistent object.
     */
    T load( PK id );

    /**
     * Loads all persistent objects from the database.
     *
     * @return A list of all persistent objects.
     */
    List<T> loadAll();

    // TODO this needs to be implemented via the javax.persistence.CriteriaQuery API.
    //List<T> loadByCondition( boolean condition );

    /**
     * Updates the given detached object in the database.
     *
     * @param detachedObject The object to update.
     */
    void update( T detachedObject );

    /**
     * Removes the persistent object from the database.
     *
     * @param persistentObject The object to remove.
     */
    void remove( T persistentObject );
}
