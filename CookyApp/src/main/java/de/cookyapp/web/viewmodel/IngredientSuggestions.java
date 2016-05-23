package de.cookyapp.web.viewmodel;

import java.util.List;

/**
 * Created by Jasper on 21.05.2016.
 */
public class IngredientSuggestions {

    private List<String> ingredients;
    private boolean onlyRecipesContainingAllIngredients;

    public IngredientSuggestions () { }

    public IngredientSuggestions (List<String> ingredients) { this.ingredients = ingredients; }

    public List<String> getIngredients () { return ingredients; }

    public void setIngredients ( List<String> ingredients ) { this.ingredients = ingredients; }


    public boolean isOnlyRecipesContainingAllIngredients() {
        return onlyRecipesContainingAllIngredients;
    }

    public void setOnlyRecipesContainingAllIngredients(boolean onlyRecipesContainingAllIngredients) {
        this.onlyRecipesContainingAllIngredients = onlyRecipesContainingAllIngredients;
    }
}
