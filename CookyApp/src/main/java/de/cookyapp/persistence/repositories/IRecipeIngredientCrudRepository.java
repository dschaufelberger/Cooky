package de.cookyapp.persistence.repositories;

import java.util.List;

import de.cookyapp.persistence.entities.RecipeIngredientEntity;

/**
 * Created by Jasper on 12.04.2016.
 */
public interface IRecipeIngredientCrudRepository extends IBaseCrudRepository<RecipeIngredientEntity, Integer> {
    List<RecipeIngredientEntity> findByRecipeId( int recipeId );

    RecipeIngredientEntity findByRecipeIdAndIngredientId( int recipeId, int ingredientId );
}
