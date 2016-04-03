package de.cookyapp.authentication;

import org.springframework.security.core.Authentication;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
