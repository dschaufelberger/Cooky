package de.cookyapp.service.dto;

import java.time.LocalDateTime;

import de.cookyapp.enums.RecipeDifficulty;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.UserEntity;


/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
public class Recipe {
    private int id;
    private String name;
    private String shortDescription;
    private String imageFileName;
    private String preparation;
    private RecipeDifficulty difficulty;
    private LocalDateTime creationDate;
    private Integer calories;
    private Integer workingTime;
    private Integer cookingTime;
    private Integer restTime;
    private Short serving;
    private Byte rating;

    private UserEntity author;

    public Recipe() {
    }

    public Recipe( RecipeEntity recipeEntity ) {
        setId( recipeEntity.getId() );
        setName( recipeEntity.getName() );
        setShortDescription( recipeEntity.getShortDescription() );
        setImageFileName( recipeEntity.getImageFileName() );
        setDifficulty( recipeEntity.getDifficulty() );
        setCalories( recipeEntity.getCalories() );
        setServing( recipeEntity.getServing() );
        setRating( recipeEntity.getRating() );
        setCreationDate( recipeEntity.getCreationTime() );
        setPreparation( recipeEntity.getPreparation() );
        setCookingTime( recipeEntity.getCookingTime() );
        setRestTime( recipeEntity.getRestTime() );
        setWorkingTime( recipeEntity.getWorkingTime() );
        setAuthor( recipeEntity.getAuthor() );
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription( String shortDescription ) {
        this.shortDescription = shortDescription;
    }

    public String getImageFileName() { return imageFileName; }

    public void setImageFileName( String imageFileName ) { this.imageFileName = imageFileName; }

    public RecipeDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty( RecipeDifficulty difficulty ) {
        this.difficulty = difficulty;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories( Integer calories ) {
        this.calories = calories;
    }

    public Short getServing() {
        return serving;
    }

    public void setServing( Short serving ) {
        this.serving = serving;
    }

    public Byte getRating() {
        return rating;
    }

    public void setRating( Byte rating ) {
        this.rating = rating;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor( UserEntity author ) {
        this.author = author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate( LocalDateTime creationDate ) {
        this.creationDate = creationDate;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation( String preparation ) {
        this.preparation = preparation;
    }

    public Integer getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime( Integer workingTime ) {
        this.workingTime = workingTime;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime( Integer cookingTime ) {
        this.cookingTime = cookingTime;
    }

    public Integer getRestTime() {
        return restTime;
    }

    public void setRestTime( Integer restTime ) {
        this.restTime = restTime;
    }
}
