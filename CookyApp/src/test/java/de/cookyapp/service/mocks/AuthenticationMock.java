package de.cookyapp.service.mocks;

import java.util.Collection;

import de.cookyapp.authentication.IAuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Dominik Schaufelberger on 26.04.2016.
 */
public class AuthenticationMock implements IAuthenticationFacade {
    private String username;

    public AuthenticationMock( String username ) {
        this.username = username;
    }

    @Override
    public Authentication getAuthentication() {
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated( boolean isAuthenticated ) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return username;
            }
        };

        return authentication;
    }
}