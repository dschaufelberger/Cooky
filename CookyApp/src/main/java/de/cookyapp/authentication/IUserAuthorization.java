package de.cookyapp.authentication;

import org.springframework.security.core.Authentication;

/**
 * Created by Dominik Schaufelberger on 05.04.2016.
 */
public interface IUserAuthorization {
    boolean hasAllAuthorities( Authentication authentication, String[] authorities );

    boolean hasAnyAuthority( Authentication authentication, String[] authorities );

    boolean hasAuthority( Authentication authentication, String authority );
}
