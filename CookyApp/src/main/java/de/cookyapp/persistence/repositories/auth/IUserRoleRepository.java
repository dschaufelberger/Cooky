package de.cookyapp.persistence.repositories.auth;

import de.cookyapp.persistence.entities.UserRoleEntity;
import de.cookyapp.persistence.entities.UserRoleEntityPK;
import org.springframework.data.repository.Repository;

/**
 * Created by Dominik Schaufelberger on 14.05.2016.
 */
public interface IUserRoleRepository extends Repository<UserRoleEntity, UserRoleEntityPK> {
    UserRoleEntity save( UserRoleEntity entity );

    void delete( UserRoleEntity deleted );

    UserRoleEntity findOne( UserRoleEntityPK id );
}
