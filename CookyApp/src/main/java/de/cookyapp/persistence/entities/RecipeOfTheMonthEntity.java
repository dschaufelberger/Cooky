package de.cookyapp.persistence.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
 * Created by Mario on 23.05.2016.
 */
@Entity
@Table( name = "RecipeOfTheMonth", schema = "Cooky_Dev" )
public class RecipeOfTheMonthEntity {
    private int id;
    private LocalDate updated;
    private String imagename;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        de.cookyapp.persistence.entities.RecipeOfTheMonthEntity that = (de.cookyapp.persistence.entities.RecipeOfTheMonthEntity) o;

        if ( id != that.id )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * this.id;
    }

    private RecipeEntity recipe;

    @ManyToOne( optional = false )
    @JoinColumn( name = "RecipeID" )
    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe( RecipeEntity recipe ) {
        this.recipe = recipe;
    }

    @Basic
    @Column( name = "UpdatedOn", nullable = true )
    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated( LocalDate updated ) {
        this.updated = updated;
    }

    @Basic
    @Column( name = "ImageName", nullable = true )
    public String getImageName() {
        return imagename;
    }

    public void setImageName( String imagename ) {
        this.imagename = imagename;
    }
}





