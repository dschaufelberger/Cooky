package de.cookyapp.web;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.cookyapp.enums.AccountState;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.service.services.interfaces.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Dominik Schaufelberger on 16.05.2016.
 */
@Component
public class CookyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private IUserCrudService userCrudService;
    private IUserLoginService userLoginService;

    @Autowired
    public CookyAuthenticationSuccessHandler( IUserCrudService userCrudService, IUserLoginService userLoginService ) {
        this.userCrudService = userCrudService;
        this.userLoginService = userLoginService;
    }

    @Override
    public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws ServletException, IOException {
        super.onAuthenticationSuccess( request, response, authentication );

        User user = this.userCrudService.getCurrentUser();

        if ( user != null ) {
            this.userLoginService.updateLastLoginDateFor( user.getId(), LocalDateTime.now() );

            if ( user.getAccountState() == AccountState.REGISTERED ) {
                this.userLoginService.updateAccountStateForUser( user.getId(), AccountState.ACTIVE );
            }
        }
    }
}
