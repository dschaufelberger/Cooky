package de.cookyapp.controller;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;

import de.cookyapp.enums.AccountState;
import de.cookyapp.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.viewmodel.registration.User;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
@Controller
@RequestMapping( "/registration" )
public class RegistrationController {
    UserDao userDao;

    @Autowired
    public RegistrationController( UserDao userDao ) {
        this.userDao = userDao;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView registrationForm() {
        return new ModelAndView( "/registration/RegistrationForm", "user", new User() );
    }

    @RequestMapping( method = RequestMethod.POST )
    public String registrationSubmit( @ModelAttribute( "user" ) @Valid User user, BindingResult bindingResult ) {
        String view;
        if ( bindingResult.hasErrors() ) {
            view = "/registration/RegistrationForm";
        } else {
            boolean isUniqueUser = true;
            List<UserEntity> list = this.userDao.loadAll();

            for ( UserEntity ue : list ) {
                if ( user.getUsername().equals( ue.getUsername() ) ) {
                    isUniqueUser = false;
                }
            }

            if ( isUniqueUser ) {
                view = "/registration/RegistrationSuccess";
                UserEntity entity = new UserEntity( user );
                entity.setAccountState( AccountState.REGISTERED );
                entity.setRegistrationDate( LocalDateTime.now() );
                this.userDao.save( entity );
            } else {
                bindingResult.addError( new FieldError( "user", "username", "Der Benutzername ist bereits vergeben. Bitte wählen Sie einen Neuen." ) );
                view = "/registration/RegistrationForm";
            }
        }

        return view;
    }
}
