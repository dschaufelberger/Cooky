package de.cookyapp.enums;

/**
 * Created by Mario on 28.05.2016.
 */
public enum SearchType {
    RECIPES("Recipes"),
    USERS("Users");

    private String searchType;

    SearchType( String searchType ) {
        this.searchType = searchType;
    }

    public String getSearchType() {
        return searchType;
    }

    @Override
    public String toString() {
        return this.searchType;
    }
}
