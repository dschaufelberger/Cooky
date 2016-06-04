package de.cookyapp.persistence.entities;

import javax.persistence.*;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "Category", schema = "Cooky_Dev" )
public class CategoryEntity {
    private String name;
    private String superCategory;

    @Id
    @Column( name = "Name", nullable = false, length = 30 )
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Basic
    @Column( name = "SuperCategory", nullable = true, length = 30 )
    public String getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory( String superCategory ) {
        this.superCategory = superCategory;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        CategoryEntity that = (CategoryEntity) o;

        if ( name != null ? !name.equals( that.name ) : that.name != null )
            return false;
        if ( superCategory != null ? !superCategory.equals( that.superCategory ) : that.superCategory != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (superCategory != null ? superCategory.hashCode() : 0);
        return result;
    }
}
