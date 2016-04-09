package de.cookyapp.web.controller;

import javax.validation.Valid;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.service.services.UserCrudService;
import de.cookyapp.web.viewmodel.account.Password;
import de.cookyapp.web.viewmodel.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mario on 27.11.2015.
 */
@Controller
@RequestMapping( "/account" )
public class UserAccountController {
    private PasswordEncoder passwordEncoder;
    private UserCrudService userCrudService;
    private IAuthenticationFacade authentication;

    @Autowired
    public UserAccountController( PasswordEncoder passwordEncoder, UserCrudService userCrudService, IAuthenticationFacade authentication ) {
        this.passwordEncoder = passwordEncoder;
        this.userCrudService = userCrudService;
        this.authentication = authentication;
    }

    @RequestMapping( "/details" )
    public ModelAndView showAccount() {
        de.cookyapp.service.dto.User userDTO = userCrudService.getUserByUsername( this.authentication.getAuthentication().getName() );

        if ( userDTO != null ) {
            User user = new User( userDTO );
            ModelAndView model = new ModelAndView( "UserAccountTile" );
            //model.addObject( "user", new User( userCrudService.load( id ) ) );
            model.addObject( "user", user );
            model.addObject( "password", new Password() );
            return model;

        } else {
            //TODO [Dodo - 10.01.16] das wird später nicht mehr nötig sein, da der bereich generell geschützt wird => nicht eingeloggt -> verweis auf login seite
            ModelAndView model = new ModelAndView( "authentication/LoginPage" );
            return model;
        }

    }

    @RequestMapping( "/changePassword" )
    public ModelAndView showPasswordForm() {
        ModelAndView model = new ModelAndView( "UserPasswordTile" );
        model.addObject( "password", new Password() );

        return model;
    }

    @RequestMapping( "/edit" )
    public String saveData( @ModelAttribute( "user" ) @Valid User user, BindingResult bindingResult ) {

        if ( bindingResult.hasErrors() ) {
            return "UserAccountTile";
        } else {
            if ( this.authentication.getAuthentication().getName().equals( user.getUsername() ) ) {
                de.cookyapp.service.dto.User userDTO = new de.cookyapp.service.dto.User();
                userDTO.setId( user.getId() );
                userDTO.setForename( user.getForename() );
                userDTO.setSurname( user.getSurname() );
                userDTO.setEmail( user.getEmail() );

                userCrudService.updateUser( userDTO );
                return "redirect:/account/details";
            } else {
                return "UserAccountTile";
            }

        }
    }

    @RequestMapping( "/validatePassword" )
    public String changePassword( @ModelAttribute( "password" ) @Valid Password password, BindingResult bindingResult ) {


        if ( bindingResult.hasErrors() ) {
            return "UserPasswordTile";
        } else {
            de.cookyapp.service.dto.User user = userCrudService.getUserByUsername( this.authentication.getAuthentication().getName() );
            //User user = userCrudService.load( password.getId() );
            if ( this.passwordEncoder.matches( password.getOldpassword(), user.getPassword() ) ) {
                if ( password.getNewpassword() != null && password.getNewpassword().equals( password.getPassword_confirm() ) ) {
                    user.setPassword( this.passwordEncoder.encode( password.getNewpassword() ) );
                    userCrudService.updateUser( user );
                    return "redirect:/account/details";
                } else {
                    bindingResult.addError( new FieldError( "user", "password_confirm", "Das neue Passwort und dessen Bestätigung stimmen nicht überein. Bitte geben Sie beide Passwröter korrekt ein." ) );
                    return "UserPasswordTile";
                }

            } else {
                bindingResult.addError( new FieldError( "user", "oldpassword", "Ihr eingegebenes Passwort stimmt nicht mit dem aktuellen Passwort überein. Bitte geben Sie Ihr aktuelles Passwort ein." ) );
                return "/UserPasswordTile";
            }
        }
    }
}