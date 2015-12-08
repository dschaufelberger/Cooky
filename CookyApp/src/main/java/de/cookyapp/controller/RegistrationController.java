package de.cookyapp.controller;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;

import de.cookyapp.enums.AccountState;
import de.cookyapp.persistence.dao.AddressDao;
import de.cookyapp.persistence.dao.UserDao;
import de.cookyapp.persistence.entities.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserDao userDao;
    private AddressDao addressDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController( UserDao userDao, AddressDao addressDao, PasswordEncoder passwordEncoder ) {
        this.userDao = userDao;
        this.addressDao = addressDao;
        this.passwordEncoder = passwordEncoder;
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
                Collection<AddressEntity> addresses = this.addressDao.loadAll();
                AddressEntity address = user.getAddress().createAddressEntity();

                for ( AddressEntity addressEntity : addresses ) {
                    if ( address.equals( addressEntity ) ) {
                        address = addressEntity;
                        break;
                    }
                }

                UserEntity userEntity = user.createUserEntity();
                userEntity.setPassword( this.passwordEncoder.encode( user.getPassword() ) );
                userEntity.setAddress( address );
                userEntity.setAccountState( AccountState.REGISTERED );
                userEntity.setRegistrationDate( LocalDateTime.now() );

                this.userDao.save( userEntity );

                view = "/registration/RegistrationSuccess";
            } else {
                bindingResult.addError( new FieldError( "user", "username", "Der Benutzername ist bereits vergeben. Bitte wählen Sie einen Neuen." ) );
                view = "/registration/RegistrationForm";
            }
        }

        return view;
    }
}
