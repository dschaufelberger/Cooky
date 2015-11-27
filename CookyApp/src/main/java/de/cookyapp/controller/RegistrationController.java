package de.cookyapp.controller;

import javax.validation.Valid;

import de.cookyapp.viewmodel.registration.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView registrationForm() {
        return new ModelAndView( "/registration/RegistrationForm", "user", new User() );
    }

    @RequestMapping( method = RequestMethod.POST )
    public String registrationSubmit( @ModelAttribute("user")@Valid User user, BindingResult bindingResult) {
        String view = bindingResult.hasErrors() ? "/registration/RegistrationForm" : "registration/RegistrationSuccess";

        return view;
    }
}
