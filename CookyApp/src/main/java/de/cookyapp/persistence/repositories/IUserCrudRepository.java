package de.cookyapp.persistence.repositories;

import de.cookyapp.persistence.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
public interface IUserCrudRepository extends IBaseCrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
