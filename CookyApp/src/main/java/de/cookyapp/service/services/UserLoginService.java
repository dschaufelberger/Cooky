package de.cookyapp.service.services;

import java.time.LocalDateTime;

import de.cookyapp.enums.AccountState;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.service.exceptions.InvalidUserId;
import de.cookyapp.service.services.interfaces.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 16.05.2016.
 */
@Service
@Transactional
public class UserLoginService implements IUserLoginService {
    private IUserCrudRepository userRepository;

    @Autowired
    public UserLoginService( IUserCrudRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateLastLoginDateFor( int userId, LocalDateTime lastLoginDate ) {
        UserEntity user = this.userRepository.findOne( userId );

        if ( user == null ) {
            throw new InvalidUserId( userId );
        }

        user.setLastLoginDate( lastLoginDate );
        this.userRepository.save( user );
    }

    @Override
    public void updateAccountStateForUser( int userId, AccountState accountState ) {
        UserEntity user = this.userRepository.findOne( userId );

        if ( user == null ) {
            throw new InvalidUserId( userId );
        }

        user.setAccountState( accountState != null ? accountState : AccountState.INACTIVE );
        this.userRepository.save( user );
    }
}
