package de.cookyapp.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by Dominik on 23.11.2015.
 */
public class CookbookRecipeEntityPK implements Serializable {
    private int cookbookId;
    private int recipeId;

    @Column( name = "CookbookID", nullable = false )
    @Id
    public int getCookbookId() {
        return cookbookId;
    }

    public void setCookbookId( int cookbookId ) {
        this.cookbookId = cookbookId;
    }

    @Column( name = "RecipeID", nullable = false )
    @Id
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId( int recipeId ) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        CookbookRecipeEntityPK that = (CookbookRecipeEntityPK) o;

        if ( cookbookId != that.cookbookId )
            return false;
        if ( recipeId != that.recipeId )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cookbookId;
        result = 31 * result + recipeId;
        return result;
    }
}
