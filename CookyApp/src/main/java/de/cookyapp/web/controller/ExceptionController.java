package de.cookyapp.web.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dominik Schaufelberger on 22.05.2016.
 */
@ControllerAdvice
public class ExceptionController {
    private Logger logger = Logger.getLogger( ExceptionController.class );

    @ExceptionHandler( value = Exception.class )
    public ModelAndView defaultExceptionHandler( Exception exception, Principal principal, HttpServletRequest request ) {
        return createResponseModel( "DefaultExceptionTile", principal, request, exception );
    }

    private ModelAndView createResponseModel( String view, Principal principal, HttpServletRequest request, Exception exception ) {
        logger.error( "Cookyapp Exception (url=\"" + request.getRequestURL() + "\"" +
                ", username=\"" + principal.getName() + "\")", exception );

        ModelAndView model = new ModelAndView( view );

        model.addObject( "exception", exception );
        model.addObject( "url", request.getRequestURL() );
        model.addObject( "username", principal != null ? principal.getName() : "anonymous" );

        return model;
    }
}
