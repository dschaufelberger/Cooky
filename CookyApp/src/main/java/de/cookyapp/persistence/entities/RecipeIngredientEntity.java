package de.cookyapp.persistence.entities;

import javax.persistence.*;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "RecipeIngredient", schema = "Cooky_Dev" )
public class RecipeIngredientEntity {
    private int id;
    private String amount;
    private String unit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    //@Basic
    //@Column( name = "RecipeID", nullable = false )
    //public int getRecipeId() {
    //    return recipeId;
    //}
    //
    //public void setRecipeId( int recipeId ) {
    //    this.recipeId = recipeId;
    //}
    //
    //@Basic
    //@Column( name = "IngredientID", nullable = false )
    //public int getIngredientId() {
    //    return ingredientId;
    //}
    //
    //public void setIngredientId( int ingredientId ) {
    //    this.ingredientId = ingredientId;
    //}

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

    private IngredientEntity ingredient;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false )
    @JoinColumn(name = "IngredientID")
    public IngredientEntity getIngredient() {
        return ingredient;
    }

    public void setIngredient( IngredientEntity ingredient ) {
        this.ingredient = ingredient;
    }

    private RecipeEntity recipe;

    @ManyToOne( optional = false )
    @JoinColumn(name = "RecipeID" )
    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe( RecipeEntity recipe ) {
        this.recipe = recipe;
    }
}