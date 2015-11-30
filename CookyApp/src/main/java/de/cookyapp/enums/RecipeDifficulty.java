package de.cookyapp.enums;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
public enum RecipeDifficulty {
    EASY("Easy"), MEDIUM("Medium"), ADVANCED("Advanced"), EXPERT("Expert");

    private String recipeDifficulty;

    RecipeDifficulty(String recipeDifficulty) {
        this.recipeDifficulty = recipeDifficulty;
    }

    public String getRecipeDifficulty() {
        return recipeDifficulty;
    }
}
