package de.cookyapp.web.viewmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.cookyapp.enums.RecipeDifficulty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Jasper on 30.11.2015.
 */
public class Recipe {
    private int id;

    @NotBlank( message = "Bitte geben Sie einen Rezeptnamen an." )
    @Size( max = 100, message = "Der Name darf nur aus maximal 100 Zeichen bestehen." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß0-9\\w\\s]+(-?[a-zA-ZäöüÄÖÜß0-9\\w\\s]*)*$", message = "Der Name darf nur aus Klein- und Großbuchstaben, einem Bindestrich und Zahlen bestehen." )
    private String name;

    @NotBlank( message = "Bitte geben Sie eine kurze Beschreibung des Rezpetes an" )
    @Size( max = 500, message = "Die Beschreibung darf höchstesn 500 Zeichen enthalten" )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß0-9!?:()\\w\\s]+(-?[a-zA-ZäöüÄÖÜß0-9!?:()\\w\\s]*)*$", message = "Die Beschreibung darf nur aus Klein- und Großbuchstaben, Bindestrichen und Zahlen bestehen." )
    private String shortDescription;

    @Max( 255 )
    private Short serving;

    @NotBlank( message = "Bitte geben Sie die Zubereitungs Hinweise ein" )
    @Size( max = 1000, message = "Die Zubereitungs Hinweise dürfen höchstens 1000 Zeichen enthalten" )
    @Pattern( regexp = "^[\\w\\säöüÄÖÜß!:()]+(-?[\\w\\säöüÄÖÜß:()]*)*$", message = "Die Zubereitungs Hinweise dürfen nur Klein- und Großbuchstaben, Zahlen, Bindestriche sowie Ausrufezeichen, Fragezeichen, Doppelpunkt und öffnende bzw. schließende Klammern" )
    private String preparation;

    @Max( value = 65535, message = "Werte nur bis 65535" )
    private Integer calories;

    @NotNull( message = "Bitte wählen Sie einen der vorgegebenen Werte." )
    private RecipeDifficulty difficulty;

    @Max( 16777215 )
    private int workingTime;

    @Max( 16777215 )
    private int cookingTime;

    @Max( 16777215 )
    private int restTime;

    private Collection<Ingredient> ingredients;

    public Recipe() {
        ingredients = new ArrayList<>();
    }

    public Recipe( de.cookyapp.service.dto.Recipe recipe, List<de.cookyapp.service.dto.Ingredient> ingredientList ) {
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
        for ( de.cookyapp.service.dto.Ingredient entity : ingredientList ) {
            Ingredient current = ingredientToViewmodelIngredient( entity );
            this.ingredients.add( current );
        }
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

    public Short getServing() {
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

    public Integer getCalories() {
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

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients( Collection<Ingredient> ingredients ) {
        this.ingredients = ingredients;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime( int restTime ) {
        this.restTime = restTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    private Ingredient ingredientToViewmodelIngredient( de.cookyapp.service.dto.Ingredient ingredient ) {
        Ingredient ingredientViewmodel = new Ingredient();
        ingredientViewmodel.setAmount( ingredient.getAmount() );
        ingredientViewmodel.setUnit( ingredient.getUnit() );
        ingredientViewmodel.setId( ingredient.getId() );
        ingredientViewmodel.setName( ingredient.getName() );
        return ingredientViewmodel;
    }
}
