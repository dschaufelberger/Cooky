package de.cookyapp.controller;

import javax.validation.Valid;

import de.cookyapp.viewmodel.authentication.LoginCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dominik Schaufelberger on 29.11.2015.
 */
@Controller
public class LoginController {

    @RequestMapping( path = "/login", method = RequestMethod.GET )
    public ModelAndView showLoginForm() {
        return new ModelAndView( "authentication/LoginForm", "loginForm", new LoginCredentials() );
    }

    @RequestMapping("/welcome")
    public @ResponseBody String welcome() {
        return "Hi Dodo!";
    }

    @RequestMapping("/secured")
    public @ResponseBody String secured() {
        return "Ich bin geschützt! :-)";
    }

    @RequestMapping("/loginError")
    public @ResponseBody String loginError() {
        return "You shall not pass!";
    }

    /*@RequestMapping( "submitLogin" )
    public String performLogin( @ModelAttribute( "loginForm" ) @Valid LoginCredentials loginCredentials, BindingResult bindingResult ) {
        String view;

        if ( bindingResult.hasErrors() ) {
            System.out.println("Ich hab Fehler");
            view = "authentication/LoginForm";
        } else {
            System.out.println("Ich hab keine Fehler");
            view = "forward:/j_security_check";
        }

        return view;
    }*/
}
