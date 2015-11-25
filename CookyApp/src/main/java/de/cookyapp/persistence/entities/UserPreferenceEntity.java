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
@Table( name = "UserPreference", schema = "Cooky_Dev", catalog = "" )
public class UserPreferenceEntity {
    private int id;
    private int userId;
    private String categoryName;

    @Id
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "UserID", nullable = false )
    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId ) {
        this.userId = userId;
    }

    @Basic
    @Column( name = "CategoryName", nullable = false, length = 30 )
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName( String categoryName ) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        UserPreferenceEntity that = (UserPreferenceEntity) o;

        if ( id != that.id )
            return false;
        if ( userId != that.userId )
            return false;
        if ( categoryName != null ? !categoryName.equals( that.categoryName ) : that.categoryName != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }
}
