package de.cookyapp.web.controller;

import java.security.Principal;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;

import de.cookyapp.service.exceptions.InvalidCookbookId;
import de.cookyapp.service.exceptions.UserNotAuthorized;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Dominik Schaufelberger on 22.05.2016.
 */
@ControllerAdvice
public class ExceptionController {
    private Logger logger = Logger.getLogger( ExceptionController.class );

    @ExceptionHandler( value = UserNotAuthorized.class )
    public ModelAndView userNotAuthorizedHandler( UserNotAuthorized exception, Principal principal, Authentication authentication, HttpServletRequest request ) {
        ModelAndView model = createResponseModel( "UserNotAuthorizedTile", exception, principal, request );
        model.addObject( "authorities", authentication != null ? authentication.getAuthorities() : new LinkedList<>() );

        return model;
    }

    @ExceptionHandler( value = InvalidCookbookId.class )
    public ModelAndView invalidCookbookIdHandler( InvalidCookbookId exception, Principal principal, HttpServletRequest request ) {
        ModelAndView model = createResponseModel( "InvalidCookbookIdTile", exception, principal, request );
        model.addObject( "id", exception.getId() );

        return model;
    }

    @ExceptionHandler( value = Exception.class )
    public ModelAndView defaultExceptionHandler( Exception exception, Principal principal, HttpServletRequest request ) {
        return createResponseModel( "DefaultExceptionTile", exception, principal, request );
    }

    @ExceptionHandler( value = NoHandlerFoundException.class )
    public String noHandlerFound404( NoHandlerFoundException exception ) {
        logger.error( "HTTP 404 Error " + exception.getHttpMethod() + " " + exception.getRequestURL() );
        return "404Tile";
    }

    private ModelAndView createResponseModel( String view, Exception exception, Principal principal, HttpServletRequest request ) {
        logger.error( "Cookyapp Exception (url=\"" + request.getRequestURL() + "\"" +
                ", username=\"" + principal.getName() + "\")", exception );

        ModelAndView model = new ModelAndView( view );

        model.addObject( "exception", exception );
        model.addObject( "url", request.getRequestURL() );
        model.addObject( "username", principal != null ? principal.getName() : "anonymous" );

        return model;
    }
}
