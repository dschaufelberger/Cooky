package de.cookyapp.service.services;

import de.cookyapp.enums.Role;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.entities.UserRoleEntity;
import de.cookyapp.persistence.entities.UserRoleEntityPK;
import de.cookyapp.persistence.repositories.IUserCrudRepository;
import de.cookyapp.persistence.repositories.IUserRoleRepository;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.InvalidUserId;
import de.cookyapp.service.services.interfaces.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 14.05.2016.
 */
@Service
@Transactional
public class UserRoleService implements IUserRoleService {
    private IUserRoleRepository roleRepository;
    private IUserCrudRepository userRepository;

    @Autowired
    public UserRoleService( IUserRoleRepository recipeRepository, IUserCrudRepository userRepository ) {
        this.roleRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addRoleToUser( User user, Role role ) {
        if ( user == null || role == null ) {
            throw new IllegalArgumentException();
        }

        UserEntity userEntity = this.userRepository.findOne( user.getId() );

        if ( userEntity == null ) {
            throw new InvalidUserId( user.getId() );
        }

        UserRoleEntityPK id = new UserRoleEntityPK();
        id.setRole( role.name() );
        id.setUsername( user.getUsername() );

        if ( this.roleRepository.findOne( id ) == null ) {
            UserRoleEntity roleEntity = new UserRoleEntity();
            roleEntity.setRole( role.name() );
            roleEntity.setUsername( userEntity.getUsername() );
            this.roleRepository.save( roleEntity );
        }
    }

    @Override
    public void removeRoleFromUser( User user, Role role ) {
        if ( user == null || role == null ) {
            throw new IllegalArgumentException();
        }

        UserEntity userEntity = this.userRepository.findOne( user.getId() );

        if ( userEntity == null ) {
            throw new InvalidUserId( user.getId() );
        }
        UserRoleEntityPK id = new UserRoleEntityPK();
        id.setRole( role.name() );
        id.setUsername( user.getUsername() );
        UserRoleEntity roleEntity = this.roleRepository.findOne( id );

        if ( roleEntity != null ) {
            this.roleRepository.delete( roleEntity );
        }
    }
}
