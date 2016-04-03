package de.cookyapp.service.services.interfaces;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.IUserCrudRepository;
import de.cookyapp.service.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
@Service
public class UserCrudService implements IUserCrudService {
    private IUserCrudRepository userCrudRepository;
    private IAuthenticationFacade authentication;

    @Autowired
    public UserCrudService( IUserCrudRepository userCrudRepository, IAuthenticationFacade authenticationFacade ) {
        this.userCrudRepository = userCrudRepository;
        this.authentication = authenticationFacade;
    }

    @Override
    public void deleteUser( String username ) {

    }

    @Override
    public void deleteUser( int id ) {

    }

    @Override
    public void deleteUser( User user ) {
    }

    @Override
    public void createUser( User user ) {

    }

    @Override
    public void updateUser( User user ) {

    }

    @Override
    public User getUserByID( int userID ) {
        return getUserIfExistant( this.userCrudRepository.findOne( userID ) );
    }

    @Override
    public User getUserByUsername( String username ) {
        return getUserIfExistant( this.userCrudRepository.findByUsername( username ) );
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
        return false;
    }

    @Override
    public boolean userExsists( String username ) {
        return false;
    }

    private User getUserIfExistant( UserEntity userEntity) {
        return userEntity == null ? null : new User( userEntity );
    }
}
