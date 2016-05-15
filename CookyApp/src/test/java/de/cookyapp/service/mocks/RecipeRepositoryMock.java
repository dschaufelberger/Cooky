package de.cookyapp.service.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;

/**
 * Created by Dominik Schaufelberger on 26.04.2016.
 */
public class RecipeRepositoryMock implements IRecipeCrudRepository {
    private List<RecipeEntity> entities;
    private int idCounter = 1;

    public RecipeRepositoryMock() {
        this( new ArrayList<>() );
    }

    public RecipeRepositoryMock( List<RecipeEntity> entities ) {
        this.entities = entities;
    }

    public List<RecipeEntity> getEntities() {
        return entities;
    }

    public void setEntities( List<RecipeEntity> entities ) {
        for ( RecipeEntity entity : entities ) {
            this.entities.add( createCopy( entity ) );
        }
    }

    @Override
    public List<RecipeEntity> findByName( String name ) {
        return this.entities
                .stream()
                .filter( entity -> entity.getName().equals( name ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<RecipeEntity> findByNameLike( String name ) {
        return this.entities
                .stream()
                .filter( entity -> entity.getName().contains( name ) )
                .collect( Collectors.toList() );
    }

    @Override
    public void delete( RecipeEntity deleted ) {
        this.entities.remove( deleted );
    }

    @Override
    public void delete( Iterable<? extends RecipeEntity> entities ) {
        for ( RecipeEntity entity : entities ) {
            delete( entity );
        }
    }

    @Override
    public List<RecipeEntity> findAll() {
        return this.entities;
    }

    @Override
    public RecipeEntity findOne( Integer integer ) {
        Optional<RecipeEntity> recipeEntity = this.entities
                .stream()
                .filter( entity -> entity.getId().equals( integer ) )
                .findFirst();

        return recipeEntity.isPresent() ? recipeEntity.get() : null;
    }

    @Override
    public RecipeEntity save( RecipeEntity persisted ) {
        RecipeEntity toAdd = createCopy( persisted );

        if ( !this.entities.remove( toAdd ) ) {
            toAdd.setId( idCounter++ );
        }
        this.entities.add( toAdd );

        return toAdd;
    }

    @Override
    public <S extends RecipeEntity> Iterable<S> save( Iterable<S> persistedEntities ) {
        List<S> entities = new ArrayList<>();

        for ( S persistedEntity : persistedEntities ) {
            entities.add( (S) save( persistedEntity ) );
        }

        return entities;
    }

    private RecipeEntity createCopy( RecipeEntity entity ) {
        RecipeEntity copy = new RecipeEntity();

        copy.setId( entity.getId() );
        copy.setName( entity.getName() );
        copy.setAuthor( entity.getAuthor() );
        copy.setCalories( entity.getCalories() );
        copy.setCookingTime( entity.getCookingTime() );
        copy.setDifficulty( entity.getDifficulty() );
        copy.setPreparation( entity.getPreparation() );
        copy.setRating( entity.getRating() );
        copy.setRestTime( entity.getRestTime() );
        copy.setServing( entity.getServing() );
        copy.setWorkingTime( entity.getWorkingTime() );
        copy.setShortDescription( entity.getShortDescription() );

        return copy;
    }
}
