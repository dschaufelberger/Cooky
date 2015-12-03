package de.cookyapp.viewmodel;

import de.cookyapp.enums.RecipeDifficulty;
import org.hibernate.validator.constraints.NotBlank;

import de.cookyapp.persistence.entities.RecipeEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Created by Jasper on 30.11.2015.
 */
public class Recipe {
    @NotBlank( message = "Bitte geben Sie einen Rezeptnamen an." )
    @Size( max = 100, message = "Der Name darf nur aus maximal 100 Zeichen bestehen." )
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß0-9]+(-?[a-zA-ZäöüÄÖÜß0-9]*)*$", message = "Der Name darf nur aus Klein- und Großbuchstaben, einem Bindestrich und Zahlen bestehen." )
    private String name;

    @NotBlank(message = "Bitte geben Sie eine kurze Beschreibung des Rezpetes an")
    @Size(max = 500, message = "Die Beschreibung darf höchstesn 500 Zeichen enthalten")
    @Pattern( regexp = "^[a-zA-ZäöüÄÖÜß0-9!?:()]+(-?[a-zA-ZäöüÄÖÜß0-9!?:()]*)*$", message = "Die Beschreibung darf nur aus Klein- und Großbuchstaben, Bindestrichen und Zahlen bestehen.")
    private String shortDescription;

    @Max( 255 )
    private Short serving;

    @NotBlank(message = "Bitte geben Sie die Zubereitungs Hinweise ein")
    @Size( max = 1000, message = "Die Zubereitungs Hinweise dürfen höchstens 1000 Zeichen enthalten")
    @Pattern( regexp = "^[\\w\\säöüÄÖÜß!:()]+(-?[\\w\\säöüÄÖÜß:()]*)*$", message = "Die Zubereitungs Hinweise dürfen nur Klein- und Großbuchstaben, Zahlen, Bindestriche sowie Ausrufezeichen, Fragezeichen, Doppelpunkt und öffnende bzw. schließende Klammern")
    private String preparation;

    @Max(value = 65535, message = "Werte nur bis 255")
    private Integer calories;

    @NotNull( message = "Bitte wählen Sie einen der vorgegebenen Werte." )
    private RecipeDifficulty difficulty;

    @Max(500)
    private int workingTime;

    @Max(500)
    private int cookingTime;

    public Recipe() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription () {
        return  shortDescription;
    }

    public void setShortDescription (String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Short getServing() {
        return serving;
    }

    public void setServing(Short serving) {
        this.serving = serving;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public RecipeDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(RecipeDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public RecipeDifficulty[] getAvailableDifficulty() {
        return RecipeDifficulty.values();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }
}
