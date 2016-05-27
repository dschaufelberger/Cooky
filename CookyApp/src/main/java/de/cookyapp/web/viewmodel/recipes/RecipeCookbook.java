package de.cookyapp.web.viewmodel.recipes;

/**
 * Created by Dominik Schaufelberger on 18.05.2016.
 */
public class RecipeCookbook {
    private int recipeId;
    private int cookbookId;

    public RecipeCookbook() {
    }

    public int getCookbookId() {
        return cookbookId;
    }

    public void setCookbookId( int cookbookId ) {
        this.cookbookId = cookbookId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId( int recipeId ) {
        this.recipeId = recipeId;
    }
}
