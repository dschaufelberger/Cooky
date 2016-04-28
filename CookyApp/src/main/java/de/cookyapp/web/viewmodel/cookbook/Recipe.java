package de.cookyapp.web.viewmodel.cookbook;

/**
 * Created by Dominik Schaufelberger on 22.04.2016.
 */
public class Recipe {
    private int id;
    private String name;
    private String descrption;
    private byte rating;

    public Recipe( de.cookyapp.service.dto.Recipe recipe) {
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.descrption = recipe.getShortDescription();
        this.rating = recipe.getRating();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescrption() {
        return descrption;
    }

    public byte getRating() {
        return rating;
    }

    //TEST
    public Recipe() {
    }

    public void setId( int id ) {
        this.id = id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setDescrption( String descrption ) {
        this.descrption = descrption;
    }

    public void setRating( byte rating ) {
        this.rating = rating;
    }
}
