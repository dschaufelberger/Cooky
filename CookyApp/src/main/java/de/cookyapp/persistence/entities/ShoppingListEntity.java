package de.cookyapp.persistence.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "ShoppingList", schema = "Cooky_Dev" )
@IdClass( ShoppingListEntityPK.class )
public class ShoppingListEntity {
    private int userId;
    private int ingredientId;
    private String amount;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "UserID", nullable = false )
    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId ) {
        this.userId = userId;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "IngredientID", nullable = false )
    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId( int ingredientId ) {
        this.ingredientId = ingredientId;
    }

    @Basic
    @Column( name = "Amount", nullable = true, length = 20 )
    public String getAmount() {
        return amount;
    }

    public void setAmount( String amount ) {
        this.amount = amount;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        ShoppingListEntity that = (ShoppingListEntity) o;

        if ( userId != that.userId )
            return false;
        if ( ingredientId != that.ingredientId )
            return false;
        if ( amount != null ? !amount.equals( that.amount ) : that.amount != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + ingredientId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    private UserEntity owner;

    @ManyToOne( optional = false )
    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner( UserEntity owner ) {
        this.owner = owner;
    }

    private IngredientEntity ingredient;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false )
    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient( IngredientEntity ingredient ) {
        this.ingredient = ingredient;
    }
}
