package de.cookyapp.persistence.repositories.app;

import java.util.List;

import de.cookyapp.persistence.entities.IngredientEntity;


/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
public interface IIngredientCrudRepository extends IBaseCrudRepository<IngredientEntity, Integer> {
    IngredientEntity findFirstByName( String ingredient );

    List<IngredientEntity> findByNameContaining( String ingredient );

    List<IngredientEntity> findByNameIn( List<String> ingredientNames );
}
