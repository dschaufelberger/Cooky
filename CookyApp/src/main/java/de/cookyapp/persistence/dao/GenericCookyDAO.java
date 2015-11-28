package de.cookyapp.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dominik Schaufelberger on 28.11.2015.
 */
public interface GenericCookyDAO <T, PK extends Serializable> {
    PK save( T transientObject );

    T load( PK id );

    List<T> loadAll();

    // TODO this needs to be implemented via the javax.persistence.CriteriaQuery API.
    //List<T> loadByCondition( boolean condition );

    void update( T detachedObject );

    void remove( T persistentObject );
}
