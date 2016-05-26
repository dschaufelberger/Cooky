package de.cookyapp.service.dto;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
    private String preparation;
    private RecipeDifficulty difficulty;
    private LocalDateTime creationDate;
    private Integer calories;
    private Integer workingTime;
    private Integer cookingTime;
    private Integer restTime;
    private Short serving;
    private Byte rating;
    private Integer voteCount;
    private String imageLink;
    private byte[] imageData;
    private User author;
    private List<Ingredient> ingredients;

    public Recipe() {
        this.ingredients = new LinkedList<>();
    }

    public Recipe( RecipeEntity recipeEntity ) {
        this();

        setId( recipeEntity.getId() );
        setName( recipeEntity.getName() );
        setShortDescription( recipeEntity.getShortDescription() );
        setDifficulty( recipeEntity.getDifficulty() );
        setCalories( recipeEntity.getCalories() );
        setServing( recipeEntity.getServing() );
        setRating( recipeEntity.getRating() );
        setCreationDate( recipeEntity.getCreationTime() );
        setPreparation( recipeEntity.getPreparation() );
        setCookingTime( recipeEntity.getCookingTime() );
        setRestTime( recipeEntity.getRestTime() );
        setWorkingTime( recipeEntity.getWorkingTime() );
        setAuthor( userEntityToUser( recipeEntity.getAuthor() ) );
        setVoteCount( recipeEntity.getVoteCount() );
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink( String imageLink ) {
        this.imageLink = imageLink;
    }

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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount( Integer voteCount ) {
        this.voteCount = voteCount;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor( User author ) {
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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData( byte[] imageData ) {
        this.imageData = imageData;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients( List<Ingredient> ingredients ) {
        this.ingredients = ingredients;
    }

    private User userEntityToUser( UserEntity userEntity ) {
        User user = new User( userEntity );
        return user;
    }
}
