package de.cookyapp.service.mocks;

import java.util.List;

import de.cookyapp.service.services.interfaces.ICookbookContentService;

/**
 * Created by Dominik Schaufelberger on 16.05.2016.
 */
public class CookbookContentServiceMock implements ICookbookContentService {
    @Override
    public void addRecipeToCookbook( int cookbookID, int recipeID ) {

    }

    @Override
    public void addRecipeToDefaultCookbook( int cookbookId, int recipeId ) {

    }

    @Override
    public void addRecipesToCookbook( int cookbookID, List<Integer> recipesIDs ) {

    }

    @Override
    public void removeRecipeFromCookbook( int cookbookID, int recipeID ) {

    }

    @Override
    public void removeAllRecipesFromCookbook( int cookbookID ) {

    }

    @Override
    public void moveRecipeBetweenCookbooks( int recipeId, int currentCookbookId, int newCookbookId ) {

    }
}
