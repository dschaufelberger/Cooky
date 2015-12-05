package de.cookyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dominik Schaufelberger on 03.12.2015.
 */
@Controller
@RequestMapping("/")
public class WelcomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String displayWelcomePage() {
        System.out.println("Danke für den Besuch!");
        return "Welcome";
    }

    @RequestMapping("dodo")
    public String displayIndexPage() {
        return "Index";
    }
}
