package de.cookyapp.service.mocks;

import java.util.LinkedList;
import java.util.List;

import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;

/**
 * Created by Dominik Schaufelberger on 26.05.2016.
 */
public class IngredientServiceMock implements IIngredientCrudService {
    @Override
    public void saveRecipeIngredient( int recipeId, List<Ingredient> ingredients ) {

    }

    @Override
    public List<Ingredient> loadRecipeIngredients( int recipeId ) {
        return new LinkedList<>();
    }
}
