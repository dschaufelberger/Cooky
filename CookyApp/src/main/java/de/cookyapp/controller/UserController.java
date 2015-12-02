package de.cookyapp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import de.cookyapp.persistence.dao.UserDao;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.viewmodel.account.Password;
import de.cookyapp.viewmodel.account.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mario on 27.11.2015.
 */
@Controller
@RequestMapping( "/" )
public class UserController {


    @RequestMapping( "/account" )
    public ModelAndView showAccount( @RequestParam( "id" ) int id , Principal principal) {


        String username = principal.getName();
        //TODO: User anhand des Usernames laden

        UserDao userdao = new UserDao();
        ModelAndView model = new ModelAndView( "accountForm" );
        model.addObject( "user", new User( userdao.load( id ) ) );
        model.addObject( "password", new Password( userdao.load( id ) ) );
        return model;
    }

    @RequestMapping( "/showPasswordForm" )
    public ModelAndView showPasswordForm( @RequestParam( "id" ) int id ) {


        UserDao userdao = new UserDao();
        ModelAndView model = new ModelAndView( "passwordForm" );
        model.addObject( "password", new Password( userdao.load( id ) ) );

        return model;
    }

    @RequestMapping( "/user" )
    public ModelAndView showAllUsers() {

        UserDao userdao = new UserDao();

        ModelAndView model = new ModelAndView( "userList" );
        List userList = new ArrayList<UserEntity>();
        userList = userdao.loadAll();
        model.addObject( "userList", userList );
        return model;
    }

    @RequestMapping( "/editUserData" )
    public String saveData( @ModelAttribute( "user" ) @Valid User user, BindingResult bindingResult ) {
        if ( bindingResult.hasErrors() ) {
            return "/accountForm";
            //TODO messages dem User anzeigen
        } else {
            UserDao userDao = new UserDao();
            userDao.editUser( user.getId(), user.getForename(), user.getSurname(), user.getEmail() );
            return "redirect:/user";
        }
    }

    @RequestMapping( "/changePassword" )
    public String changePassword( @ModelAttribute( "password" ) @Valid Password password, BindingResult bindingResult ) {

        if ( bindingResult.hasErrors() ) {
            return "/passwordForm";
            //TODO messages dem User anzeigen
        } else {
            UserDao userDao = new UserDao();
            UserEntity user = userDao.load( password.getId() );
            if ( password.getOldpassword().equals( user.getPassword() ) ) {
                user.setPassword( password.getNewpassword() );
                userDao.update( user );
                return "redirect:/user";
            } else {
                return "/passwordForm";
                //TODO: Das eingegebene Passwort ist nicht richtig!
            }
        }
    }
}
