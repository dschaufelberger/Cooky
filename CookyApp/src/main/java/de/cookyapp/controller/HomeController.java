package de.cookyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.cookyapp.viewmodel.authentication.LoginCredentials;

/**
 * Created by Dominik Schaufelberger on 01.12.2015.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET )
    public ModelAndView displayHomePage() {
        ModelAndView modelAndView = new ModelAndView( "Index" );
        modelAndView.addObject( "userCredentials", new LoginCredentials() );

        return modelAndView;
    }
}
