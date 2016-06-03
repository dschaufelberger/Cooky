package de.cookyapp.persistence.entities;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "RecipeIngredient", schema = "Cooky_Dev" )
public class RecipeIngredientEntity {
    private int id;
    private String amount;
    private String unit;
    private IngredientEntity ingredient;
    private RecipeEntity recipe;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "Amount", nullable = true, length = 20 )
    public String getAmount() {
        return amount;
    }

    public void setAmount( String amount ) {
        this.amount = amount;
    }

    @Basic
    @Column( name = "Unit", nullable = true, length = 20 )
    public String getUnit() {
        return unit;
    }

    public void setUnit( String unit ) {
        this.unit = unit;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        RecipeIngredientEntity that = (RecipeIngredientEntity) o;

        if ( id != that.id )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * this.id;
    }

    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.EAGER, optional = false )
    @JoinColumn(name = "IngredientID")
    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient( IngredientEntity ingredient ) {
        this.ingredient = ingredient;
    }

    @ManyToOne( optional = false )
    @JoinColumn(name = "RecipeID" )
    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe( RecipeEntity recipe ) {
        this.recipe = recipe;
    }
}