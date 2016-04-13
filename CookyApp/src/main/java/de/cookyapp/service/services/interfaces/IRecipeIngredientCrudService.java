package de.cookyapp.service.services.interfaces;

import de.cookyapp.service.dto.Ingredient;

import java.util.List;

/**
 * Created by Jasper on 12.04.2016.
 */
public interface IRecipeIngredientCrudService {
    String loadAmount (int id);

    String loadUnit (int id);

    List<Ingredient> getRecipeIngredients (int recipeId);

    void saveRecipeIngredient (int recipeId, Ingredient ingredient);
}
