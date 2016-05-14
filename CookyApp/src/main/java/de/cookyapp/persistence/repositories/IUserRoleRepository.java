package de.cookyapp.persistence.repositories;

import de.cookyapp.persistence.entities.UserRoleEntity;
import de.cookyapp.persistence.entities.UserRoleEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dominik Schaufelberger on 14.05.2016.
 */
public interface IUserRoleRepository extends JpaRepository<UserRoleEntity, UserRoleEntityPK> {
}
