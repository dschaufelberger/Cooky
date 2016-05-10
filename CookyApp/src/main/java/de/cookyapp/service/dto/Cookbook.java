package de.cookyapp.service.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.enums.CookbookVisibility;
import de.cookyapp.persistence.entities.CookbookEntity;

/**
 * Created by Dominik Schaufelberger on 21.04.2016.
 */
public class Cookbook {
    private int id;
    private String name;
    private String shortDescription;
    private CookbookVisibility visibility;
    private LocalDateTime creationTime;
    private User owner;
    private List<Recipe> recipes;

    public Cookbook() {
        this.recipes = new ArrayList<>();
    }

    public Cookbook( CookbookEntity cookbookEntity ) {
        setId( cookbookEntity.getId() );
        setName( cookbookEntity.getName() );
        setShortDescription( cookbookEntity.getShortDescription() );
        setVisibility( cookbookEntity.getVisibility() );
        setCreationTime( cookbookEntity.getCreationTime() );
        setOwner( new User( cookbookEntity.getOwner() ) );

        ArrayList<Recipe> recipes = cookbookEntity.getRecipes().stream().map( Recipe::new ).collect( Collectors.toCollection( ArrayList::new ) );
        setRecipes( recipes );
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

    public CookbookVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility( CookbookVisibility visibility ) {
        this.visibility = visibility;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime( LocalDateTime creationTime ) {
        this.creationTime = creationTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner( User owner ) {
        this.owner = owner;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes( List<Recipe> recipes ) {
        this.recipes = recipes;
    }
}
