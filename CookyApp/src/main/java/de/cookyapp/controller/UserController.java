package de.cookyapp.controller;

import java.util.ArrayList;
import java.util.List;

import de.cookyapp.persistence.dao.UserDao;
import de.cookyapp.persistence.entities.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mario on 27.11.2015.
 */
@Controller
@RequestMapping("/")
public class UserController {


    @RequestMapping("/account")
    public ModelAndView showAccount( @RequestParam("id") int id) {


        UserDao userdao = new UserDao();
        ModelAndView model = new ModelAndView( "account" );
        model.addObject( "user" , userdao.load( id ));

        return model;

    }
    @RequestMapping("/user")
    public ModelAndView showAllUsers(){

        UserDao userdao = new UserDao();

        ModelAndView model = new ModelAndView( "user" );
        List userList = new ArrayList<UserEntity>( );
        userList = userdao.loadAll();
        model.addObject( "userList" , userList);
        return model;
    }
    @RequestMapping("/editUserData")
    public String saveData( @RequestParam("id") int id, @RequestParam("forename") String forename, @RequestParam("surname") String surname, @RequestParam("email") String email, @RequestParam("password") String password) {
        UserDao userDao = new UserDao();
        userDao.editUser( id, forename, surname, email, password);
        return "redirect:/user";
    }

}
