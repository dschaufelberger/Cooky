package de.cookyapp.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Dominik on 23.11.2015.
 */
public class RecipeTagEntityPK implements Serializable {
    private int recipeId;
    private int tagId;

    @Column( name = "RecipeID", nullable = false )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId( int recipeId ) {
        this.recipeId = recipeId;
    }

    @Column( name = "TagID", nullable = false )
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    public int getTagId() {
        return tagId;
    }

    public void setTagId( int tagId ) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        RecipeTagEntityPK that = (RecipeTagEntityPK) o;

        if ( recipeId != that.recipeId )
            return false;
        if ( tagId != that.tagId )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recipeId;
        result = 31 * result + tagId;
        return result;
    }
}
