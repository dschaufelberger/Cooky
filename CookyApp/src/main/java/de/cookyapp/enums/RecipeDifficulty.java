package de.cookyapp.enums;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
public enum RecipeDifficulty {
    NONE( "None" ), EASY( "Easy" ), MEDIUM( "Medium" ), ADVANCED( "Advanced" ), EXPERT( "Expert" );

    private String displayName;

    RecipeDifficulty( String displayName ) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
