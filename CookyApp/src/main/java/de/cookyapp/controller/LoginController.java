package de.cookyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dominik Schaufelberger on 29.11.2015.
 */
@Controller
public class LoginController {
    @RequestMapping( "/loginPage" )
    public String loginPage() {
        return "LoginTile";
    }

    @RequestMapping( "/loginError" )
    public String loginError() {
        return "LoginErrorTile";
    }
}
