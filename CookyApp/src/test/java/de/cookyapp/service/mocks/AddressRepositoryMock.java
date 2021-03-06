package de.cookyapp.service.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.cookyapp.persistence.entities.AddressEntity;
import de.cookyapp.persistence.repositories.app.IAddressCrudRepository;

/**
 * Created by Mario on 03.05.2016.
 */
public class AddressRepositoryMock implements IAddressCrudRepository {
    private List<AddressEntity> entities;
    private int idCounter = 1;


    public AddressRepositoryMock() {
        this( new ArrayList<>() );
    }

    public AddressRepositoryMock( List<AddressEntity> entities ) {
        this.entities = entities;
    }

    public List<AddressEntity> getEntities() {
        return entities;
    }

    public void setEntities( List<AddressEntity> entities ) {
        for ( AddressEntity entity : entities ) {
            this.entities.add( createCopy( entity ) );
        }
    }

    @Override
    public void delete( AddressEntity deleted ) {
        this.entities.remove( deleted );
    }

    @Override
    public void delete( Iterable<? extends AddressEntity> entities ) {
        for ( AddressEntity entity : entities ) {
            delete( entity );
        }
    }

    @Override
    public List<AddressEntity> findAll() {
        return this.entities;
    }

    @Override
    public AddressEntity findOne( Integer integer ) {
        Optional<AddressEntity> addressEntity = this.entities.stream().filter( entity -> entity.getId() == integer ).findFirst();

        return addressEntity.isPresent() ? addressEntity.get() : null;
    }

    @Override
    public AddressEntity save( AddressEntity persisted ) {
        AddressEntity address = createCopy( persisted );

        if ( !this.entities.remove( address ) ) {
            address.setId( idCounter++ );
        }
        this.entities.add( address );

        return address;
    }

    @Override
    public <S extends AddressEntity> Iterable<S> save( Iterable<S> persistedEntities ) {
        List<S> entities = new ArrayList<>();

        for ( S persistedEntity : persistedEntities ) {
            AddressEntity entity = save( persistedEntity );
            if ( entity != null ) {
                entities.add( (S) entity );
            }
        }
        return entities;
    }

    private AddressEntity createCopy( AddressEntity entity ) {
        AddressEntity copy = new AddressEntity();

        copy.setCity( entity.getCity() );
        copy.setHouseNumber( entity.getHouseNumber() );
        copy.setId( entity.getId() );
        copy.setPostcode( entity.getPostcode() );
        copy.setStreet( entity.getStreet() );

        return copy;
    }
}