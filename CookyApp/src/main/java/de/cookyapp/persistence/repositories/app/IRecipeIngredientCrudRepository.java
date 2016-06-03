package de.cookyapp.persistence.repositories.app;

import java.util.List;

import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Jasper on 12.04.2016.
 */
public interface IRecipeIngredientCrudRepository extends IBaseCrudRepository<RecipeIngredientEntity, Integer> {
    List<RecipeIngredientEntity> findByRecipeId( int recipeId );

    RecipeIngredientEntity findByRecipeIdAndIngredientId( int recipeId, int ingredientId );

    @Query( nativeQuery = true, value = "SELECT * FROM RecipeIngredient WHERE IngredientID in :ingredientIds GROUP BY RecipeID HAVING COUNT(DISTINCT IngredientID)>=:idCount")
    List<RecipeIngredientEntity> findRecipeIngredients (@Param("ingredientIds") List<Integer> ids,
                                                          @Param("idCount") int idcount);
}
