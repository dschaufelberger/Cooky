package de.cookyapp.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import de.cookyapp.persistence.dao.UserDao;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.viewmodel.account.Password;
import de.cookyapp.viewmodel.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Mario on 27.11.2015.
 */
@Controller
@RequestMapping( "/account" )
public class UserAccountController {
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserAccountController( PasswordEncoder passwordEncoder ) {
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping( "/details" )
    public ModelAndView showAccount(Principal principal ) {


        //String username = principal.getName();

        try{
            UserDao userdao = new UserDao();
            User user = new User( userdao.loadUserByUsername( principal.getName() ) );
            ModelAndView model = new ModelAndView( "accountForm" );
            //model.addObject( "user", new User( userdao.load( id ) ) );
            model.addObject( "user", user );
            model.addObject( "password", new Password() );
            return model;
        }catch (Exception e){
            //TODO Exception: wenn User nicht gefunden wird
            throw e;
        }

    }

    @RequestMapping( "/changePassword" )
    public ModelAndView showPasswordForm() {

        UserDao userdao = new UserDao();
        ModelAndView model = new ModelAndView( "passwordForm" );
        model.addObject( "password", new Password( ));

        return model;
    }

    /*@RequestMapping( "/userlist" )
    public ModelAndView showAllUsers() {

        UserDao userdao = new UserDao();

        ModelAndView model = new ModelAndView( "userList" );
        List userList = new ArrayList<UserEntity>();
        userList = userdao.loadAll();
        model.addObject( "userList", userList );
        return model;
    }*/

    @RequestMapping( "/edit" )
    public String saveData( @ModelAttribute( "user" ) @Valid User user, BindingResult bindingResult, Principal principal ) {
        if ( bindingResult.hasErrors() ) {
            return "accountForm";
        } else {
            if(principal.getName().equals( user.getUsername() )){
                UserDao userDao = new UserDao();
                userDao.editUser( user.getId(), user.getForename(), user.getSurname(), user.getEmail() );
                return "redirect:/account/userlist";
            }else {
                return "accountForm";
            }

        }
    }

    @RequestMapping( "/validatePassword" )
    public String changePassword( @ModelAttribute( "password" ) @Valid Password password, BindingResult bindingResult ) {


        if ( bindingResult.hasErrors() ) {
            return "/passwordForm";
        } else {
            UserDao userDao = new UserDao();
            UserEntity user = userDao.load( password.getId() );
            if ( this.passwordEncoder.matches( password.getOldpassword(),user.getPassword() )) {
                user.setPassword( this.passwordEncoder.encode( password.getNewpassword() ) );
                userDao.update( user );
                return "redirect:/account/userlist";
            } else {
                bindingResult.addError( new FieldError("user", "oldpassword", "Blablablablablablablablab"));
                return "/passwordForm";
            }
        }
    }
}