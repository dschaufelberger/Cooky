package de.cookyapp.enums;

/**
 * Created by Mario on 28.05.2016.
 */
public enum SearchType {
    RECIPES("Recipes"),
    USERS("Users");

    private String name;

    SearchType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
