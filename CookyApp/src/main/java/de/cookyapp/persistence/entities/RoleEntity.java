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
@Table( name = "Role", schema = "Cooky_Dev_Users" )
public class RoleEntity {
    private Role roleName;

    @Id
    @Enumerated( EnumType.STRING )
    @Column( name = "RoleName", nullable = false, length = 20 )
    public Role getRoleName() {
        return roleName;
    }

    public void setRoleName( Role roleName ) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        RoleEntity that = (RoleEntity) o;

        return roleName == that.roleName;

    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }
}
