package de.cookyapp.enums;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
public enum CookbookVisibility {
    PRIVATE( "Private" ), FRIENDS( "Shared" ), PUBLIC( "Public" );

    private String displayName;

    CookbookVisibility( String displayName ) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
