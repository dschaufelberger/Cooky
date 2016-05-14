package de.cookyapp.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import de.cookyapp.enums.Role;

/**
 * Created by Dominik Schaufelberger on 14.05.2016.
 */
@Entity
@Table( name = "UserRole", schema = "Cooky_Dev_Users", catalog = "" )
@IdClass( UserRoleEntityPK.class )
public class UserRoleEntity {
    private String username;
    private Role role;

    @Id
    @Column( name = "Username", nullable = false, length = 30 )
    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    @Id
    @Enumerated( EnumType.STRING )
    @Column( name = "Role", nullable = false, length = 20 )
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

        UserRoleEntity that = (UserRoleEntity) o;

        if ( !username.equals( that.username ) )
            return false;
        return role == that.role;

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
