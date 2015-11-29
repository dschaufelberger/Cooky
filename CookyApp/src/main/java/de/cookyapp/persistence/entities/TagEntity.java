package de.cookyapp.persistence.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "Tag", schema = "Cooky_Dev" )
public class TagEntity {
    private String name;
    private int id;

    @Basic
    @Column( name = "Name", nullable = false, length = 30 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Id
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        TagEntity tagEntity = (TagEntity) o;

        if ( id != tagEntity.id )
            return false;
        if ( name != null ? !name.equals( tagEntity.name ) : tagEntity.name != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
