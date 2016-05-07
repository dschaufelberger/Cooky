package de.cookyapp.service.mocks;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.cookyapp.enums.CookbookVisibility;
import de.cookyapp.persistence.entities.CookbookEntity;
import de.cookyapp.persistence.repositories.ICookbookRepository;

/**
 * Created by Dominik Schaufelberger on 07.05.2016.
 */
public class CookbookRepositoryMock implements ICookbookRepository {
    private LinkedList<CookbookEntity> entities = new LinkedList<>();
    private int idCounter = 1;

    public CookbookRepositoryMock() {
    }

    public LinkedList<CookbookEntity> getEntities() {
        return entities;
    }

    public void setEntities( LinkedList<CookbookEntity> entities ) {
        this.entities = entities;
    }

    public void addEntitiy( CookbookEntity entity ) {
        if ( entity != null ) {
            CookbookEntity copy = copyEntity( entity );
            this.entities.remove( copy );
            this.entities.add( copy );
        }
    }

    @Override
    public Collection<CookbookEntity> findByOwnerId( int ownerId ) {
        return this.entities
                .stream()
                .filter( entity -> entity.getOwner().getId() == ownerId )
                .map( entity -> copyEntity( entity ) )
                .collect( Collectors.toList() );
    }

    @Override
    public Collection<CookbookEntity> findByVisibility( CookbookVisibility visibility ) {
        return this.entities
                .stream()
                .filter( entity -> entity.getVisibility() == visibility )
                .map( entity -> copyEntity( entity ) )
                .collect( Collectors.toList() );
    }

    @Override
    public Collection<CookbookEntity> findByOwnerIdAndVisibility( int ownerId, CookbookVisibility visibility ) {
        return this.entities
                .stream()
                .filter( entity -> entity.getOwner().getId() == ownerId && entity.getVisibility() == visibility )
                .map( entity -> copyEntity( entity ) )
                .collect( Collectors.toList() );
    }

    @Override
    public CookbookEntity findByOwnerIdAndIsDefaultTrue( int ownerId ) {
        Optional<CookbookEntity> result = this.entities
                .stream()
                .filter( entity -> entity.getOwner().getId() == ownerId && entity.getIsDefault() )
                .findFirst();

        return result.isPresent() ? copyEntity( result.get() ) : null;
    }

    @Override
    public void delete( CookbookEntity deleted ) {
        this.entities.remove( deleted );
    }

    @Override
    public void delete( Iterable<? extends CookbookEntity> entities ) {
        for ( CookbookEntity entity : entities ) {
            delete( entity );
        }
    }

    @Override
    public List<CookbookEntity> findAll() {
        return this.entities
                .stream()
                .map( entity -> copyEntity( entity ) )
                .collect( Collectors.toList() );
    }

    @Override
    public CookbookEntity findOne( Integer integer ) {
        Optional<CookbookEntity> result = this.entities
                .stream()
                .filter( entity -> entity.getId() == integer )
                .findFirst();

        return result.isPresent() ? copyEntity( result.get() ) : null;
    }

    @Override
    public CookbookEntity save( CookbookEntity persisted ) {
        CookbookEntity entity = copyEntity( persisted );

        if ( !this.entities.remove( entity ) ) {
            entity.setId( idCounter++ );
        }
        this.entities.add( entity );

        return entity;
    }

    @Override
    public <S extends CookbookEntity> Iterable<S> save( Iterable<S> persistedEntities ) {
        LinkedList<S> resultList = new LinkedList<>();
        for ( S persistedEntity : persistedEntities ) {
            resultList.add( (S) save( persistedEntity ) );
        }

        return resultList;
    }

    private CookbookEntity copyEntity( CookbookEntity entity ) {
        if ( entity == null ) {
            return null;
        }

        CookbookEntity copy = new CookbookEntity();
        copy.setId( entity.getId() );
        copy.setName( entity.getName() );
        copy.setOwner( entity.getOwner() );
        copy.setVisibility( entity.getVisibility() );
        copy.setCreationTime( entity.getCreationTime() );
        copy.setShortDescription( entity.getShortDescription() );
        copy.setIsDefault( entity.getIsDefault() );
        copy.setOwnerId( entity.getOwnerId() );
        copy.setRecipes( entity.getRecipes() );

        return copy;
    }
}
