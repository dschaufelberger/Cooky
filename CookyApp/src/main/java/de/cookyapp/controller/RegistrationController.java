package de.cookyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    public String registrationView() {
        return "Registration";
    }
}
