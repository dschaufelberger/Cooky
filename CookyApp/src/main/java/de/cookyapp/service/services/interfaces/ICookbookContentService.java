package de.cookyapp.service.services.interfaces;

import java.util.List;

/**
 * Created by Dominik Schaufelberger on 02.05.2016.
 */
public interface ICookbookContentService {
    void addRecipeToCookbook( int cookbookID, int recipeID );

    void addRecipeToDefaultCookbook( int cookbookId, int recipeId );

    void addRecipesToCookbook( int cookbookID, List<Integer> recipesIDs );

    void removeRecipeFromCookbook( int cookbookID, int recipeID );

    void removeAllRecipesFromCookbook( int cookbookID );

    void moveRecipeBetweenCookbooks( int recipeId, int currentCookbookId, int newCookbookId );
}
