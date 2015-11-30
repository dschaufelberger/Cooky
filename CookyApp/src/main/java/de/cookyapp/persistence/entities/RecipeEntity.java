package de.cookyapp.persistence.entities;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import de.cookyapp.enums.RecipeDifficulty;
import de.cookyapp.viewmodel.Recipe;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@javax.persistence.Table( name = "Recipe", schema = "Cooky_Dev")
public class RecipeEntity {
    private String name;
    private String shortDescription;
    private String preparation;
    private RecipeDifficulty difficulty;
    private String imageFileName;
    private LocalDateTime creationTime;
    private Short calories;
    private Short serving;
    private Short rating;
    private int id;
    private int workingTime;
    private int cookingTime;
    private int restTime;
    private int authorId;

    public RecipeEntity () {

    }

    public RecipeEntity (Recipe recipe) {
        this.name = recipe.getName();
        this.shortDescription = recipe.getShortDescription();
        this.preparation = recipe.getPreparation();
        this.difficulty = recipe.getDifficulty();
        this.calories = recipe.getCalories();
        this.serving = recipe.getServing();
        this.workingTime = recipe.getWorkingTime();
        this.cookingTime = recipe.getCookingTime();
    }

    @Id
    @javax.persistence.Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }


    @Basic
    @javax.persistence.Column( name = "Name", nullable = false, length = 100 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }


    @Basic
    @javax.persistence.Column( name = "ShortDescription", nullable = true, length = 500 )
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription( String shortDescription ) {
        this.shortDescription = shortDescription;
    }


    @Basic
    @javax.persistence.Column( name = "Serving", nullable = true )
    public Short getServing() {
        return serving;
    }

    public void setServing( Short serving ) {
        this.serving = serving;
    }


    @Basic
    @javax.persistence.Column( name = "Preparation", nullable = true, length = -1 )
    public String getPreparation() {
        return preparation;
    }

    public void setPreparation( String preparation ) {
        this.preparation = preparation;
    }


    @Basic
    @javax.persistence.Column( name = "Calories", nullable = true )
    public Short getCalories() {
        return calories;
    }

    public void setCalories( Short calories ) {
        this.calories = calories;
    }


    @Basic
    @Enumerated( EnumType.STRING )
    @javax.persistence.Column( name = "Difficulty", nullable = true, length = 20 )
    public RecipeDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty( RecipeDifficulty difficulty ) {
        this.difficulty = difficulty;
    }


    @Basic
    @javax.persistence.Column( name = "Rating", nullable = true )
    public Short getRating() {
        return rating;
    }

    public void setRating( Short rating ) {
        this.rating = rating;
    }


    @Basic
    @javax.persistence.Column( name = "WorkingTime", nullable = true )
    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime( int workingTime ) {
        this.workingTime = workingTime;
    }


    @Basic
    @javax.persistence.Column( name = "CookingTime", nullable = true )
    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime( int cookingTime ) {
        this.cookingTime = cookingTime;
    }


    @Basic
    @javax.persistence.Column( name = "RestTime", nullable = true )
    public int getRestTime() {
        return restTime;
    }

    public void setRestTime( int restTime ) {
        this.restTime = restTime;
    }


    @Basic
    @javax.persistence.Column( name = "ImageFileName", nullable = true, length = 255 )
    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName( String imageFileName ) {
        this.imageFileName = imageFileName;
    }


    @Basic
    @javax.persistence.Column( name = "AuthorID", nullable = false )
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId( int authorId ) {
        this.authorId = authorId;
    }


    @Basic
    @javax.persistence.Column( name = "CreationTime", nullable = true )
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime( LocalDateTime creationTime ) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        RecipeEntity that = (RecipeEntity) o;

        if ( id != that.id )
            return false;
        if ( workingTime != that.workingTime )
            return false;
        if ( cookingTime != that.cookingTime )
            return false;
        if ( restTime != that.restTime )
            return false;
        if ( authorId != that.authorId )
            return false;
        if ( name != null ? !name.equals( that.name ) : that.name != null )
            return false;
        if ( shortDescription != null ? !shortDescription.equals( that.shortDescription ) : that.shortDescription != null )
            return false;
        if ( serving != null ? !serving.equals( that.serving ) : that.serving != null )
            return false;
        if ( preparation != null ? !preparation.equals( that.preparation ) : that.preparation != null )
            return false;
        if ( calories != that.calories )
            return false;
        if ( difficulty != null ? !difficulty.equals( that.difficulty ) : that.difficulty != null )
            return false;
        if ( rating != null ? !rating.equals( that.rating ) : that.rating != null )
            return false;
        if ( imageFileName != null ? !imageFileName.equals( that.imageFileName ) : that.imageFileName != null )
            return false;
        if ( creationTime != null ? !creationTime.equals( that.creationTime ) : that.creationTime != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (serving != null ? serving.hashCode() : 0);
        result = 31 * result + (preparation != null ? preparation.hashCode() : 0);
        result = 31 * result +  calories;
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + workingTime;
        result = 31 * result + cookingTime;
        result = 31 * result + restTime;
        result = 31 * result + (imageFileName != null ? imageFileName.hashCode() : 0);
        result = 31 * result + authorId;
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        return result;
    }
}
