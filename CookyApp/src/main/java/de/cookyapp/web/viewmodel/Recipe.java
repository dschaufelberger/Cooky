package de.cookyapp.web.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.cookyapp.enums.RecipeDifficulty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Jasper on 30.11.2015.
 */
public class Recipe {
    private int id;

    @NotBlank( message = "Bitte geben Sie einen Rezeptnamen an." )
    @Size( max = 100, message = "Der Name darf nur aus maximal 100 Zeichen bestehen." )
    private String name;

    @NotBlank( message = "Bitte geben Sie eine kurze Beschreibung des Rezpetes an" )
    @Size( max = 500, message = "Die Beschreibung darf höchstesn 500 Zeichen enthalten" )
    private String shortDescription;

    @Max( 255 )
    private short serving;

    @NotBlank( message = "Bitte geben Sie die Zubereitungs Hinweise ein" )
    private String preparation;

    @Max( value = 65535, message = "Werte nur bis 65535" )
    private int calories;

    @NotNull( message = "Bitte wählen Sie einen der vorgegebenen Werte." )
    private RecipeDifficulty difficulty;

    @Max( 16777215 )
    private int workingTime;

    @Max( 16777215 )
    private int cookingTime;

    @Max( 16777215 )
    private int restTime;

    private String imageLink;

    @Min( 0 )
    @Max( 5 )
    private byte rating;

    private List<Ingredient> ingredients;

    public Recipe() {
        ingredients = new ArrayList<>();
    }

    public Recipe( de.cookyapp.service.dto.Recipe recipe, List<de.cookyapp.service.dto.Ingredient> ingredients ) {
        this.id = recipe.getId();
        this.ingredients = new ArrayList<>();
        this.name = recipe.getName();
        this.shortDescription = recipe.getShortDescription();
        this.serving = recipe.getServing();
        this.preparation = recipe.getPreparation();
        this.calories = recipe.getCalories();
        this.difficulty = recipe.getDifficulty();
        this.workingTime = recipe.getWorkingTime() == null ? 0 : recipe.getWorkingTime();
        this.cookingTime = recipe.getCookingTime() == null ? 0 : recipe.getCookingTime();
        this.restTime = recipe.getRestTime() == null ? 0 : recipe.getRestTime();
        this.rating = recipe.getRating();
        this.ingredients = mapIngredientsToViewModel( ingredients );
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

    public short getServing() {
        return serving;
    }

    public void setServing( Short serving ) {
        this.serving = serving;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation( String preparation ) {
        this.preparation = preparation;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories( Integer calories ) {
        this.calories = calories;
    }

    public RecipeDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty( RecipeDifficulty difficulty ) {
        this.difficulty = difficulty;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime( int workingTime ) {
        this.workingTime = workingTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime( int cookingTime ) {
        this.cookingTime = cookingTime;
    }

    public RecipeDifficulty[] getAvailableDifficulty() {
        return RecipeDifficulty.values();
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients( List<Ingredient> ingredients ) {
        this.ingredients = ingredients;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime( int restTime ) {
        this.restTime = restTime;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating( byte rating ) {
        this.rating = rating;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink( String imageLink ) {
        this.imageLink = imageLink;
    }

    private List<Ingredient> mapIngredientsToViewModel( List<de.cookyapp.service.dto.Ingredient> ingredients ) {
        return ingredients.stream().map( ingredient -> new Ingredient( ingredient ) ).collect( Collectors.toList() );
    }
}
