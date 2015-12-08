package de.cookyapp.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "RecipeTag", schema = "Cooky_Dev" )
@IdClass( RecipeTagEntityPK.class )
public class RecipeTagEntity {
    private int recipeId;
    private int tagId;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "RecipeID", nullable = false )
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId( int recipeId ) {
        this.recipeId = recipeId;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "TagID", nullable = false )
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

        RecipeTagEntity that = (RecipeTagEntity) o;

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
