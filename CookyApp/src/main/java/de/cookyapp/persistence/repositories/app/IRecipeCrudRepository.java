package de.cookyapp.persistence.repositories.app;

import java.util.List;

import de.cookyapp.persistence.entities.RecipeEntity;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
public interface IRecipeCrudRepository extends IBaseCrudRepository<RecipeEntity, Integer> {
    List<RecipeEntity> findByName( String name );

    List<RecipeEntity> findByNameContaining( String name );

    List<RecipeEntity> findByIngredientsIngredientName_In( List<String> ingredientName );

}
