package de.cookyapp.controller;

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
@RequestMapping("/")
public class UserController {


    @RequestMapping("/account")
    public ModelAndView showAccount( @RequestParam("id") int id) {


        UserDao userdao = new UserDao();
        ModelAndView model = new ModelAndView("accountForm");
        model.addObject( "user" , new User(userdao.load( id )));
        model.addObject( "password", new Password(userdao.load( id )) );

        return model;
    }
    @RequestMapping("/user")
    public ModelAndView showAllUsers(){

        UserDao userdao = new UserDao();

        ModelAndView model = new ModelAndView("userList");
        List userList = new ArrayList<UserEntity>( );
        userList = userdao.loadAll();
        model.addObject( "userList" , userList);
        return model;
    }
    @RequestMapping("/editUserData")
    public String saveData(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "accountForm";
            //TODO messages dem User anzeigen
        }else {
            UserDao userDao = new UserDao();
            userDao.editUser( user.getId(), user.getForename(), user.getSurname(), user.getEmail() );
            return "redirect:/user";
        }
}
    @RequestMapping("/changePassword")
    public String changePassword( @ModelAttribute("password") @Valid Password password, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "accountForm";
            //TODO messages dem User anzeigen
            //TODO bei Falschangabe auf die gleiche Seite verweisen (Problem: keine ID und kein user in Model)
        }else {
            //TODO mit JQuery auf gleiches Passwort prüfen
            UserDao userDao = new UserDao();
            UserEntity user = userDao.load( 6);
            // Vor allem vergleichst du hier dann auch das neue Passwort mit dem alten Benutzerpasswort.
            // In deinem Password.java brauchst du ja 3 Felder: oldPassword, newPassword und confirmPassword.
            if(password.getOldpassword() == user.getPassword()) {
                user.setPassword( password.getNewpassword() );
                userDao.update( user );
                return "/user";
            }else {
                //Das Problem ist, dass wenn du die Form zurückgibst, erwartet er eben auch das User Objekt, das ist aber nicht mehr vorhanden.
                //d.h. du müsstest die Detailseite hier komplett neu laden.
                //ODER: Du machst es dir einfach und packst alles in eine Form vorerst, dann kann man das Passwort halt nicht gertennt ändern auf der Seite
                //Das wäre für unser Zeitproblem vorerst das Beste! ;-)
                return "redirect:/account";
                //TODO: Falsches Passwort eingegeben!
                //TODO bei Falschangabe auf die gleiche Seite verweisen (Problem: keine ID und kein user in Model)
            }


        }
    }

}
