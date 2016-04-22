package de.cookyapp.persistence.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.cookyapp.enums.CookbookVisibility;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "Cookbook", schema = "Cooky_Dev" )
public class CookbookEntity {
    private int id;
    private String name;
    private String shortDescription;
    private CookbookVisibility visibility;
    private boolean isDefault;
    int ownerId;
    private LocalDateTime creationTime;

    private UserEntity owner;
    private Collection<RecipeEntity> recipes;

    @Id
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "Name", nullable = false, length = 70 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Basic
    @Column( name = "ShortDescription", nullable = true, length = 1000 )
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription( String shortDescription ) {
        this.shortDescription = shortDescription;
    }

    @Basic
    @Enumerated( EnumType.STRING )
    @Column( name = "Visibility", nullable = false, length = 10 )
    public CookbookVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility( CookbookVisibility visibility ) {
        this.visibility = visibility;
    }

    @Basic
    @Column( name = "IsDefault", nullable = false )
    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault( boolean aDefault ) {
        this.isDefault = aDefault;
    }

    @Basic
    @Column( name = "OwnerID", nullable = false )
    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId( int ownerId ) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column( name = "CreationTime", nullable = true )
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime( LocalDateTime creationTime ) {
        this.creationTime = creationTime;
    }

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false )
    @JoinColumn( name = "OwnerID" )
    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner( UserEntity owner ) {
        this.owner = owner;
    }

    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable(name = "CookbookRecipe", joinColumns = @JoinColumn(name = "CookbookID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "RecipeID", referencedColumnName = "ID")
    )
    public Collection<RecipeEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes( Collection<RecipeEntity> recipes ) {
        this.recipes = recipes;
    }


    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        CookbookEntity that = (CookbookEntity) o;

        if ( id != that.id )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return 31 * id;
    }
}
