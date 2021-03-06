package de.cookyapp.web.viewmodel.cookbook;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import de.cookyapp.enums.CookbookVisibility;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Dominik Schaufelberger on 22.04.2016.
 */
public class Cookbook {
    private int id;
    private int ownerId;

    @NotBlank( message = "Please enter a name for the cookbook." )
    private String name;

    @NotBlank( message = "Please enter a short description text for the cookbook." )
    private String shortDescription;
    private String ownerUsername;

    @NotNull( message = "Please select a visibility for the recipe." )
    private CookbookVisibility visibility;
    private List<Recipe> recipes;

    public Cookbook() {
    }

    public Cookbook( de.cookyapp.service.dto.Cookbook cookbook ) {
        this.id = cookbook.getId();
        this.name = cookbook.getName();
        this.shortDescription = cookbook.getShortDescription();
        this.visibility = cookbook.getVisibility();
        this.ownerId = cookbook.getOwner().getId();
        this.ownerUsername = cookbook.getOwner().getUsername();
        this.recipes = cookbook.getRecipes().stream().map( recipe -> new Recipe( recipe ) ).collect( Collectors.toList() );
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

    public void setVisibility( CookbookVisibility visibility ) {
        this.visibility = visibility;
    }

    public int getRecipeCount() {
        return getRecipes().size();
    }

    public CookbookVisibility[] getVisibilities() {
        return CookbookVisibility.values();
    }
}
