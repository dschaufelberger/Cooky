package de.cookyapp.persistence.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    private boolean editable;
    private int ownerId;
    private LocalDateTime creationTime;

    private UserEntity owner;

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
    @Column( name = "Editable", nullable = false )
    public boolean isEditable() {
        return editable;
    }

    public void setEditable( boolean editable ) {
        this.editable = editable;
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        CookbookEntity that = (CookbookEntity) o;

        if ( id != that.id )
            return false;
        if ( editable != that.editable )
            return false;
        if ( ownerId != that.ownerId )
            return false;
        if ( name != null ? !name.equals( that.name ) : that.name != null )
            return false;
        if ( shortDescription != null ? !shortDescription.equals( that.shortDescription ) : that.shortDescription != null )
            return false;
        if ( visibility != null ? !visibility.equals( that.visibility ) : that.visibility != null )
            return false;
        if ( creationTime != null ? !creationTime.equals( that.creationTime ) : that.creationTime != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
        result = 31 * result + (editable ? 1 : 0);
        result = 31 * result + ownerId;
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        return result;
    }

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false )
    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner( UserEntity owner ) {
        this.owner = owner;
    }
}
