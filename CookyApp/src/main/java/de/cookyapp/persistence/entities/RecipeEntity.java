package de.cookyapp.persistence.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import de.cookyapp.enums.RecipeDifficulty;
//import de.cookyapp.service.viewmodel.Recipe;
//TODO check imports

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@javax.persistence.Table( name = "Recipe", schema = "Cooky_Dev" )
public class RecipeEntity {
    private int id;
    private String name;
    private String shortDescription;
    private String preparation;
    private RecipeDifficulty difficulty;
    private String imageFileName;
    private LocalDateTime creationTime;
    private Integer calories;
    private Short serving;
    private Byte rating;
    private Integer workingTime;
    private Integer cookingTime;
    private Integer restTime;

    private UserEntity author;
    private Collection<CommentEntity> comments;
    private Collection<CookbookEntity> containingCookbooks;
    //private Collection<TagEntity> tags;
    private Collection<RecipeIngredientEntity> ingredients;

    public RecipeEntity () {

    }

    //public RecipeEntity (Recipe recipe) {
    //    this.name = recipe.getName();
    //    this.shortDescription = recipe.getShortDescription();
    //    this.preparation = recipe.getPreparation();
    //    this.difficulty = recipe.getDifficulty();
    //    this.calories = recipe.getCalories();
    //    this.serving = recipe.getServing();
    //    this.workingTime = recipe.getWorkingTime();
    //    this.cookingTime = recipe.getCookingTime();
    //    this.restTime = recipe.getRestTime();
    //}

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
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
    public Integer getCalories() {
        return calories;
    }

    public void setCalories( Integer calories ) {
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
    public Byte getRating() {
        return rating;
    }

    public void setRating( Byte rating ) {
        this.rating = rating;
    }


    @Basic
    @javax.persistence.Column( name = "WorkingTime", nullable = true )
    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime( Integer workingTime ) {
        this.workingTime = workingTime;
    }


    @Basic
    @javax.persistence.Column( name = "CookingTime", nullable = true )
    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime( Integer cookingTime ) {
        this.cookingTime = cookingTime;
    }


    @Basic
    @javax.persistence.Column( name = "RestTime", nullable = true )
    public Integer getRestTime() {
        return restTime;
    }

    public void setRestTime( Integer restTime ) {
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
    @javax.persistence.Column( name = "CreationTime", nullable = true )
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime( LocalDateTime creationTime ) {
        this.creationTime = creationTime;
    }


    @OneToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinColumn( name = "AuthorID" )
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor( UserEntity author ) {
        this.author = author;
    }


    /*@OneToMany( cascade = CascadeType.ALL, mappedBy = "commentedRecipe" )
    public Collection<CommentEntity> getComments() {
        return comments;
    }

    public void setComments( Collection<CommentEntity> comments ) {
        this.comments = comments;
    }*/


    @ManyToMany( mappedBy = "recipes" )
    public Collection<CookbookEntity> getContainingCookbooks() {
        return containingCookbooks;
    }

    public void setContainingCookbooks( Collection<CookbookEntity> containingCookbooks ) {
        this.containingCookbooks = containingCookbooks;
    }

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "recipe" )
    public Collection<RecipeIngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients( Collection<RecipeIngredientEntity> ingredients ) {
        this.ingredients = ingredients;
    }


    /*@ManyToMany( cascade = CascadeType.ALL )
    @JoinTable(name = "RecipeTag", joinColumns = @JoinColumn(name = "RecipeID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TagID", referencedColumnName = "ID")
    )
    public Collection<TagEntity> getTags() {
        return tags;
    }

    public void setTags( Collection<TagEntity> tags ) {
        this.tags = tags;
    } */

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
        if ( name != null ? !name.equals( that.name ) : that.name != null )
            return false;
        if ( shortDescription != null ? !shortDescription.equals( that.shortDescription ) : that.shortDescription != null )
            return false;
        if ( serving != null ? !serving.equals( that.serving ) : that.serving != null )
            return false;
        if ( preparation != null ? !preparation.equals( that.preparation ) : that.preparation != null )
            return false;
        if ( calories != null ? !calories.equals( that.calories ) : that.calories != null )
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
        result = 31 * result + (calories != null ? calories.hashCode() : 0);
        result = 31 * result + (difficulty != null ? difficulty.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + workingTime;
        result = 31 * result + cookingTime;
        result = 31 * result + restTime;
        result = 31 * result + (imageFileName != null ? imageFileName.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        return result;
    }
}
