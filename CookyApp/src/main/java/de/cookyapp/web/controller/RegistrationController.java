package de.cookyapp.web.controller;

import java.time.LocalDateTime;
import javax.validation.Valid;

import de.cookyapp.enums.AccountState;
import de.cookyapp.service.services.interfaces.IAddressService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.registration.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
@Controller
@RequestMapping( "/registration" )
public class RegistrationController {
    private IUserCrudService userCrudService;
    private IAddressService addressService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController( IUserCrudService userCrudService, IAddressService addressService, PasswordEncoder passwordEncoder ) {
        this.userCrudService = userCrudService;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView registrationForm() {
        return new ModelAndView( "RegistrationTile", "user", new User() );
    }

    @RequestMapping( method = RequestMethod.POST )
    public String registrationSubmit( @ModelAttribute( "user" ) @Valid User user, BindingResult bindingResult ) {
        String view;
        if ( bindingResult.hasErrors() ) {
            view = "RegistrationTile";
        } else {
            boolean isUniqueUser = !this.userCrudService.userExsists( user.getUsername() );

            if ( isUniqueUser ) {
                de.cookyapp.service.dto.User userDTO = new de.cookyapp.service.dto.User();
                de.cookyapp.service.dto.Address address = new de.cookyapp.service.dto.Address();

                userDTO.setForename( user.getForename() );
                userDTO.setSurname( user.getSurname() );
                userDTO.setUsername( user.getUsername() );
                userDTO.setPassword( this.passwordEncoder.encode( user.getPassword() ) );
                userDTO.setGender( user.getGender() );
                userDTO.setBirthdate( user.getBirthdate() );
                userDTO.setEmail( user.getEmail() );
                userDTO.setAccountState( AccountState.REGISTERED );
                userDTO.setRegistrationDate( LocalDateTime.now() );

                address.setStreet( user.getAddress().getStreet() );
                address.setHouseNumber( user.getAddress().getHouseNumber() );
                address.setCity( user.getAddress().getCity() );
                address.setPostcode( user.getAddress().getPostcode() );

                this.userCrudService.createUser( userDTO );
                userDTO = this.userCrudService.getUserByUsername( user.getUsername() );
                this.addressService.createAddressForUser( userDTO.getId(), address );

                view = "RegistrationSuccessTile";
            } else {
                bindingResult.addError( new FieldError( "user", "username", "Der Benutzername ist bereits vergeben. Bitte wählen Sie einen Neuen." ) );
                view = "RegistrationTile";
            }
        }

        return view;
    }
}