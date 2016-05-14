package de.cookyapp.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import de.cookyapp.enums.Role;

/**
 * Created by Dominik Schaufelberger on 14.05.2016.
 */
@Entity
@Table( name = "Role", schema = "Cooky_Dev_Users", catalog = "Cooky_Dev_Users" )
public class RoleEntity {
    private Role role;

    @Id
    @Enumerated( EnumType.STRING )
    @Column( name = "RoleName", nullable = false, length = 20 )
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

        RoleEntity that = (RoleEntity) o;

        return role == that.role;

    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
