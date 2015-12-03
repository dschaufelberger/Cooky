package de.cookyapp.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Dominik on 23.11.2015.
 */
public class ShoppingListEntityPK implements Serializable {
    private int userId;
    private int ingredientId;

    @Column( name = "UserID", nullable = false )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId ) {
        this.userId = userId;
    }

    @Column( name = "IngredientID", nullable = false )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId( int ingredientId ) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        ShoppingListEntityPK that = (ShoppingListEntityPK) o;

        if ( userId != that.userId )
            return false;
        if ( ingredientId != that.ingredientId )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + ingredientId;
        return result;
    }
}
