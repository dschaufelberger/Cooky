package de.cookyapp.controller;

import de.cookyapp.persistence.dao.AddressDao;
import de.cookyapp.persistence.dao.RecipeDao;
import de.cookyapp.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dominik Schaufelberger on 02.12.2015.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    RecipeDao recipeDao;
    //serDao userDao;
    //AddressDao addressDao;

    //,UserDao userDao,AddressDao addressDa
    @Autowired
    public TestController(  RecipeDao recipeDao ) {
        this.recipeDao = recipeDao;
        //this.userDao = userDao;
        //this.addressDao = addressDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView testHibernate() {
        ModelAndView mav = new ModelAndView( "/Test" );
        //mav.addObject( "userList", userDao.loadAll() );
        mav.addObject( "recipeList", recipeDao.loadAllWithLazyRelations() );
        //mav.addObject( "addressList", addressDao.loadAll() );

        return mav;
    }
}
