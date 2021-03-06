package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.service.dto.Recipe;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
public interface IRecipeCrudService {
    void deleteRecipe( int recipeID );

    Recipe createRecipe( Recipe recipe );

    void updateRecipe( Recipe recipe );

    Recipe getRecipe( int recipeID );

    List<Recipe> getAllRecipes();

    List<Recipe> getAllRecipesByName( String recipeName );

    List<Recipe> searchRecipesContaining( String searchTerm );

    List<Recipe> recipeSuggestions( List<String> ingredientName );
}
