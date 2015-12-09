package de.cookyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dominik Schaufelberger on 01.12.2015.
 */
@Controller
@RequestMapping( "/" )
public class HomeController {

    @RequestMapping( method = RequestMethod.GET )
    public String displayHomePage() {

        return "Index";
    }
}
