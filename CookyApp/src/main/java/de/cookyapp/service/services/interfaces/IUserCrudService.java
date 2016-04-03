package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
public interface IUserCrudService {
    void deleteUser( String username );

    void deleteUser( int id );

    void deleteUser( User user );

    void createUser( User user );

    void updateUser( User user );

    User getUserByID( int userID );

    User getUserByUsername( String username );

    User getCurrentUser();

    List<User> getAllUsers();

    boolean userExists( User user );

    boolean userExsists( String username );
}
