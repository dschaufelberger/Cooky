package de.cookyapp.authentication;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Created by Dominik Schaufelberger on 05.04.2016.
 */
@Component
public class UserAuthorization implements IUserAuthorization {
    @Override
    public boolean hasAllAuthorities( Authentication authentication, String[] authorities ) {
        return hasAuthorities( authentication, authorities );
    }

    @Override
    public boolean hasAnyAuthority( Authentication authentication, String[] authorities ) {
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        boolean isAuthorized = false;

        for ( int i = 0; i < grantedAuthorities.size() && !isAuthorized; i++ ) {
            //isAuthorized |= authoritiesContains( authorities, grantedAuthorities..getAuthority() );
        }

        return isAuthorized;
    }

    @Override
    public boolean hasAuthority( Authentication authentication, String authority ) {
        return hasAuthorities( authentication, new String[] {authority} );
    }

    private boolean hasAuthorities( Authentication authentication, String[] authorities ) {
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        boolean isAuthorized = true;

        for ( GrantedAuthority grantedAuthority : grantedAuthorities ) {
            isAuthorized &= authoritiesContains( authorities, grantedAuthority.getAuthority() );
        }

        return isAuthorized;
    }

    private boolean authoritiesContains( String[] authorities, String authority ) {
        boolean isContained = false;

        for ( int i = 0; i < authorities.length && !isContained; i++ ) {
            isContained |= authority.equals( authorities[i] );
        }

        return isContained;
    }
}
