package de.cookyapp.service.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.authentication.IUserAuthorization;
import de.cookyapp.enums.AccountState;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.IUserCrudRepository;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.InvalidUserID;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
@Transactional
@Service
public class UserCrudService implements IUserCrudService {
    private IUserCrudRepository userCrudRepository;
    private IAuthenticationFacade authentication;
    private IUserAuthorization userAuthorization;

    @Autowired
    public UserCrudService( IUserCrudRepository userCrudRepository, IAuthenticationFacade authenticationFacade, IUserAuthorization userAuthorization ) {
        this.userCrudRepository = userCrudRepository;
        this.authentication = authenticationFacade;
        this.userAuthorization = userAuthorization;
    }

    @Override
    public void deleteUser( String username ) {
        if ( username != null && !username.isEmpty() ) {
            boolean isAuthorized = this.authentication.getAuthentication().getName().equals( username );

            //TODO [dodo] define authority roles and check if the current authority is authorized for this action.
            //if (!isAuthorized) {
            //    isAuthorized = this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" );
            //}

            if ( isAuthorized ) {
                UserEntity userEntity = this.userCrudRepository.findByUsername( username );

                if ( userEntity != null ) {
                    this.userCrudRepository.delete( userEntity );
                }
            }
        }
    }

    @Override
    public void deleteUser( int id ) {
        deleteUserById( id );
    }

    @Override
    public void deleteUser( User user ) {
        deleteUserById( user.getId() );
    }

    @Override
    public void createUser( User user ) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername( user.getUsername() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setForename( user.getForename() );
        userEntity.setSurname( user.getSurname() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setGender( user.getGender() );
        userEntity.setBirthdate( user.getBirthdate() );
        userEntity.setRegistrationDate( user.getRegistrationDate() );
        userEntity.setLastLoginDate( user.getLastLoginDate() );
        userEntity.setAccountState( user.getAccountState() );
        userEntity.setAccountState( AccountState.REGISTERED );
        userEntity.setRegistrationDate( LocalDateTime.now() );

        this.userCrudRepository.save( userEntity );
    }

    @Override
    public void updateUser( User user ) {
        UserEntity userEntity = this.userCrudRepository.findOne( user.getId() );
        if ( userEntity != null ) {
            //TODO [dodo] define authority roles and check if the current authority is authorized for this action
            Authentication authentication = this.authentication.getAuthentication();
            boolean isAuthorized = true;    //authentication.getName().equals( user.getUsername() );

            //if ( !isAuthorized ) {
            //    isAuthorized = this.userAuthorization.hasAuthority( authentication, "COOKY_ADMIN" );
            //}

            if ( isAuthorized ) {
                userEntity.setForename( user.getForename() );
                userEntity.setSurname( user.getSurname() );
                userEntity.setEmail( user.getEmail() );
                this.userCrudRepository.save( userEntity );
            }
        } else {
            throw new InvalidUserID( user.getId() );
        }
    }

    @Override
    public User getUserByID( int userID ) {
        //TODO [dodo] define authority roles and check if the current authority is authorized for this action
        boolean isAuthorized = true;    // this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_USER" );
        User user = null;

        if ( isAuthorized ) {
            user = getUserIfExistant( this.userCrudRepository.findOne( userID ) );
        }

        return user;
    }

    @Override
    public User getUserByUsername( String username ) {
        //TODO [dodo] define authority roles and check if the current authority is authorized for this action
        boolean isAuthorized = true;    // this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_USER" );
        User user = null;

        if ( isAuthorized ) {
            user = getUserIfExistant( this.userCrudRepository.findByUsername( username ) );
        }

        return user;
    }

    @Override
    public User getCurrentUser() {
        String currentUsername = this.authentication.getAuthentication().getName();
        UserEntity currentUserEntity = this.userCrudRepository.findByUsername( currentUsername );
        User currentUser = getUserIfExistant( currentUserEntity );

        return currentUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> allEntities = this.userCrudRepository.findAll();
        List<User> allUsers = allEntities.stream().map( userEntity -> getUserIfExistant( userEntity ) ).collect( Collectors.<User>toList() );

        return allUsers;
    }

    @Override
    public boolean userExists( User user ) {
        return userExsists( user.getUsername() );
    }

    @Override
    public boolean userExsists( String username ) {
        return this.userCrudRepository.findByUsername( username ) != null;
    }

    private void deleteUserById( int id ) {
        UserEntity userEntity = this.userCrudRepository.findOne( id );

        if ( userEntity != null ) {
            boolean isAuthorized = this.authentication.getAuthentication().getName().equals( userEntity.getUsername() );

            //TODO [dodo] define authority roles and check if the current authority is authorized for this action.
            //if (!isAuthorized) {
            //    isAuthorized = this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" );
            //}

            if ( isAuthorized ) {
                this.userCrudRepository.delete( userEntity );
            }
        }
    }

    private User getUserIfExistant( UserEntity userEntity ) {
        return userEntity == null ? null : new User( userEntity );
    }
}
