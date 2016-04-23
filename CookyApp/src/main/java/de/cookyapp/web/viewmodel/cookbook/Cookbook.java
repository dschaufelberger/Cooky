package de.cookyapp.web.viewmodel.cookbook;

import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.enums.CookbookVisibility;

/**
 * Created by Dominik Schaufelberger on 22.04.2016.
 */
public class Cookbook {
    private int id;
    private int ownerId;
    private String name;
    private String shortDescription;
    private String ownerUsername;
    private CookbookVisibility visibility;
    private List<Recipe> recipes;

    public Cookbook( de.cookyapp.service.dto.Cookbook cookbook ) {
        this.id = cookbook.getId();
        this.name = cookbook.getName();
        this.shortDescription = cookbook.getShortDescription();
        this.visibility = cookbook.getVisibility();
        this.ownerId = cookbook.getOwner().getId();
        this.ownerUsername = cookbook.getOwner().getUsername();
        this.recipes = cookbook.getRecipes().stream().map( recipe -> new Recipe( recipe ) ).collect( Collectors.toList());
    }

    public int getId() {
        return id;
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

    public int getOwnerId() {
        return ownerId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public CookbookVisibility getVisibility() {
        return visibility;
    }

    public int getRecipeCount() {
        return getRecipes().size();
    }


    //TEST


    public Cookbook() {
    }

    public void setId( int id ) {
        this.id = id;
    }

    public void setOwnerId( int ownerId ) {
        this.ownerId = ownerId;
    }

    public void setOwnerUsername( String ownerUsername ) {
        this.ownerUsername = ownerUsername;
    }

    public void setVisibility( CookbookVisibility visibility ) {
        this.visibility = visibility;
    }

    public void setRecipes( List<Recipe> recipes ) {
        this.recipes = recipes;
    }
}
