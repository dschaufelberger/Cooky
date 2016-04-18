package de.cookyapp.service.services.interfaces;

import de.cookyapp.service.dto.Ingredient;

import java.util.List;

/**
 * Created by Jasper on 12.04.2016.
 */
public interface IIngredientCrudService {
    void deleteIngredient (int ingredientId);

    void addIngredient (Ingredient ingredient);

    void updateIngredient (Ingredient ingredient);

    Ingredient getIngredient (int ingredientId);

    void save (List<Ingredient> ingredients);

    void saveRecipeIngredient (int recipeId, List<Ingredient> ingredients);

    List<Ingredient> loadRecipeIngredients(int recipeId);
}
