package de.cookyapp.service.services;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.IUserCrudRepository;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
@Transactional
@Service
public class UserCrudService implements IUserCrudService {
    private IUserCrudRepository userCrudRepository;
    private IAuthenticationFacade authentication;

    @Autowired
    public UserCrudService(IUserCrudRepository userCrudRepository, IAuthenticationFacade authenticationFacade) {
        this.userCrudRepository = userCrudRepository;
        this.authentication = authenticationFacade;
    }

    @Override
    public void deleteUser(String username) {
        if (username == null) {
            throw new IllegalArgumentException("The username is null or empty.");
        } else {
            boolean isAuthorized = this.authentication.getAuthentication().getName().equals(username);

            //TODO [dodo] define authority roles and check if the current authority is authorized for this action.
            //if (!isAuthorized) {
            //    Collection<? extends GrantedAuthority> authorities
            //            = this.authentication.getAuthentication().getAuthorities();
            //
            //    for (GrantedAuthority authority : authorities) {
            //        "COOKY_ADMIN".equals(authority.getAuthority());
            //    }
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
    public void deleteUser(int id) {

    }

    @Override
    public void deleteUser(User user) {
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserByID(int userID) {
        return getUserIfExistant(this.userCrudRepository.findOne(userID));
    }

    @Override
    public User getUserByUsername(String username) {
        return getUserIfExistant(this.userCrudRepository.findByUsername(username));
    }

    @Override
    public User getCurrentUser() {
        String currentUsername = this.authentication.getAuthentication().getName();
        UserEntity currentUserEntity = this.userCrudRepository.findByUsername(currentUsername);
        User currentUser = getUserIfExistant(currentUserEntity);

        return currentUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> allEntities = this.userCrudRepository.findAll();
        List<User> allUsers = allEntities.stream().map(userEntity -> getUserIfExistant(userEntity)).collect(Collectors.<User>toList());

        return allUsers;
    }

    @Override
    public boolean userExists(User user) {
        return false;
    }

    @Override
    public boolean userExsists(String username) {
        return false;
    }

    private User getUserIfExistant(UserEntity userEntity) {
        return userEntity == null ? null : new User(userEntity);
    }
}
