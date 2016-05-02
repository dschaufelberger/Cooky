package de.cookyapp.web.viewmodel.cookbook;

/**
 * Created by Dominik Schaufelberger on 22.04.2016.
 */
public class Recipe {
    private int id;
    private String name;
    private String description;
    private byte rating;

    public Recipe( de.cookyapp.service.dto.Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.description = recipe.getShortDescription();
        this.rating = recipe.getRating();
    }

    //TEST
    public Recipe() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating( byte rating ) {
        this.rating = rating;
    }
}
