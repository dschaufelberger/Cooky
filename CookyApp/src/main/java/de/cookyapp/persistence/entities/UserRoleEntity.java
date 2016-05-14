package de.cookyapp.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by Dominik Schaufelberger on 14.05.2016.
 */
@Entity
@Table( name = "UserRole", schema = "Cooky_Dev_Users", catalog = "Cooky_Dev_Users" )
@IdClass( UserRoleEntityPK.class )
public class UserRoleEntity {
    private String username;
    private String role;

    @Id
    @Column( name = "Username", nullable = false, insertable = false, updatable = false, length = 30 )
    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    @Id
    @Column( name = "Role", nullable = false, insertable = false, updatable = false, length = 20 )
    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        UserRoleEntity that = (UserRoleEntity) o;

        if ( username != null ? !username.equals( that.username ) : that.username != null )
            return false;
        return role != null ? role.equals( that.role ) : that.role == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
