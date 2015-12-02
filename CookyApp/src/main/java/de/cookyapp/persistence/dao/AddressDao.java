package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.entities.AddressEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by Dominik Schaufelberger on 02.12.2015.
 */
@Repository
public class AddressDao extends GenericCookyDaoImplementation<AddressEntity, Integer> {
    public AddressDao() {
        super( AddressEntity.class );
    }

    @Override
    protected void loadLazy( AddressEntity persistentObject ) {

    }
}
