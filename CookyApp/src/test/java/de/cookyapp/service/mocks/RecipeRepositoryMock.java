package de.cookyapp.service.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.repositories.IRecipeCrudRepository;

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
    public List<RecipeEntity> findByNameContaining( String name ) {
        return this.entities
                .stream()
                .filter( entity -> entity.getName().contains( name ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<RecipeEntity> findByNameLike( String name ) {
        return this.entities
                .stream()
                .filter( entity -> entity.getName().equals( name ) )
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
                .filter( entity -> entity.getId() == integer )
                .findFirst();

        return recipeEntity.isPresent() ? recipeEntity.get() : null;
    }

    @Override
    public RecipeEntity save( RecipeEntity persisted ) {
        RecipeEntity recipeEntity = null;

        if ( persisted.getId() == 0 ) {
            persisted.setId( this.idCounter++ );
            this.entities.add( persisted );
            recipeEntity = persisted;
        } else {
            for ( RecipeEntity entity : entities ) {
                if ( entity.getId() == persisted.getId() ) {
                    entity.setName( persisted.getName() );
                    entity.setCalories( persisted.getCalories() );
                    entity.setCookingTime( persisted.getCookingTime() );
                    entity.setDifficulty( persisted.getDifficulty() );
                    entity.setImageFileName( persisted.getImageFileName() );
                    entity.setPreparation( persisted.getPreparation() );
                    entity.setRating( persisted.getRating() );
                    entity.setRestTime( persisted.getRestTime() );
                    entity.setServing( persisted.getServing() );
                    entity.setWorkingTime( persisted.getWorkingTime() );
                    entity.setShortDescription( persisted.getShortDescription() );

                    recipeEntity = entity;
                }
            }
        }

        return recipeEntity;
    }

    @Override
    public <S extends RecipeEntity> Iterable<S> save( Iterable<S> persistedEntities ) {
        List<S> entities = new ArrayList<>();

        for ( S persistedEntity : persistedEntities ) {
            RecipeEntity entity = save( persistedEntity );
            if ( entity != null ) {
                entities.add( (S) entity );
            }
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
        copy.setImageFileName( entity.getImageFileName() );
        copy.setPreparation( entity.getPreparation() );
        copy.setRating( entity.getRating() );
        copy.setRestTime( entity.getRestTime() );
        copy.setServing( entity.getServing() );
        copy.setWorkingTime( entity.getWorkingTime() );
        copy.setShortDescription( entity.getShortDescription() );

        return copy;
    }
}
