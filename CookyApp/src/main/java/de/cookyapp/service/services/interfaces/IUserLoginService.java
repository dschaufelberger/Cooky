package de.cookyapp.service.services.interfaces;

import java.time.LocalDateTime;

import de.cookyapp.enums.AccountState;

/**
 * Created by Dominik Schaufelberger on 16.05.2016.
 */
public interface IUserLoginService {
    public void updateLastLoginDateFor( int userId, LocalDateTime lastLoginDate );

    public void updateAccountStateForUser( int userId, AccountState accountState );
}
