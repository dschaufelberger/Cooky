package de.cookyapp.web.viewmodel;

import java.util.List;

/**
 * Created by Jasper on 21.05.2016.
 */
public class IngredientSuggestions {

    private List<String> ingredients;
    private boolean recipesContainingAtLeastOneIngredient;

    public IngredientSuggestions () { }

    public IngredientSuggestions (List<String> ingredients) { this.ingredients = ingredients; }

    public List<String> getIngredients () { return ingredients; }

    public void setIngredients ( List<String> ingredients ) { this.ingredients = ingredients; }


    public boolean isRecipesContainingAtLeastOneIngredient() {
        return recipesContainingAtLeastOneIngredient;
    }

    public void setRecipesContainingAtLeastOneIngredient(boolean recipesContainingAtLeastOneIngredient) {
        this.recipesContainingAtLeastOneIngredient = recipesContainingAtLeastOneIngredient;
    }
}
