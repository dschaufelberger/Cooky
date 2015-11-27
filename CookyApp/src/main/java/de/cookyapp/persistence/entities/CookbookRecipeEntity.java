package de.cookyapp.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "CookbookRecipe", schema = "Cooky_Dev" )
@IdClass( CookbookRecipeEntityPK.class )
public class CookbookRecipeEntity {
    private int cookbookId;
    private int recipeId;

    @Id
    @Column( name = "CookbookID", nullable = false )
    public int getCookbookId() {
        return cookbookId;
    }

    public void setCookbookId( int cookbookId ) {
        this.cookbookId = cookbookId;
    }

    @Id
    @Column( name = "RecipeID", nullable = false )
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

        CookbookRecipeEntity that = (CookbookRecipeEntity) o;

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
