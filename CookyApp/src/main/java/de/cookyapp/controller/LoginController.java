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
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "authentication/LoginPage";
    }

    @RequestMapping("/loginError")
    public String loginError() {
        return "authentication/LoginError";
    }
}
