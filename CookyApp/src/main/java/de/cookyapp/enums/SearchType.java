package de.cookyapp.enums;

/**
 * Created by Mario on 28.05.2016.
 */
public enum SearchType {
    RECIPES("Recipes"),
    USERS("Users"),
    BOTH("Recipes & Users");

    private String name;

    private SearchType(String name) {
        this.name = name;
    }
    public String getSearchType() {
        return this.name;
    }
}
