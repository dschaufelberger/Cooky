package de.cookyapp.controller;

import java.time.LocalDateTime;
import javax.validation.Valid;

import de.cookyapp.enums.AccountState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.cookyapp.persistence.dao.GenericCookyDAO;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.viewmodel.registration.User;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
@Controller
@RequestMapping( "/registration" )
public class RegistrationController {
    GenericCookyDAO<UserEntity, Integer> userDao;

    @Autowired
    public RegistrationController( @Qualifier("userDao") GenericCookyDAO<UserEntity, Integer> userDao ) {
        this.userDao = userDao;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView registrationForm() {
        return new ModelAndView( "/registration/RegistrationForm", "user", new User() );
    }

    @RequestMapping( method = RequestMethod.POST )
    public String registrationSubmit( @ModelAttribute( "user" ) @Valid User user, BindingResult bindingResult ) {
        String view = null;
        if ( bindingResult.hasErrors() ) {
            view = "/registration/RegistrationForm";
        } else {
            view = "/registration/RegistrationSuccess";
            UserEntity entity = new UserEntity( user );
            entity.setAccountState( AccountState.REGISTERED );
            entity.setRegistrationDate( LocalDateTime.now() );
            this.userDao.save( entity );
        }

        return view;
    }
}
