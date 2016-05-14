package de.cookyapp.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import de.cookyapp.enums.Role;

/**
 * Created by Dominik Schaufelberger on 14.05.2016.
 */
public class UserRoleEntityPK implements Serializable {
    private String username;
    private Role role;

    @Column( name = "Username", nullable = false, length = 30 )
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    @Column( name = "Role", nullable = false, length = 20 )
    @Id
    @Enumerated( EnumType.STRING )
    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        UserRoleEntityPK that = (UserRoleEntityPK) o;

        if ( username != null ? !username.equals( that.username ) : that.username != null )
            return false;
        if ( role != null ? !role.equals( that.role ) : that.role != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
