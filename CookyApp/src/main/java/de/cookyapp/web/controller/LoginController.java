package de.cookyapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dominik Schaufelberger on 29.11.2015.
 */
@Controller
public class LoginController {
    @RequestMapping( "/signin" )
    public String loginPage() {
        return "LoginTile";
    }

    @RequestMapping( "/signinError" )
    public String loginError() {
        return "LoginErrorTile";
    }
}
