package de.cookyapp.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import de.cookyapp.enums.CookbookVisibility;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.cookbook.Cookbook;
import de.cookyapp.web.viewmodel.cookbook.CookbookOverview;
import de.cookyapp.web.viewmodel.cookbook.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dominik Schaufelberger on 22.04.2016.
 */
@Controller
@RequestMapping( "/cookbooks" )
public class CookbookController {
    private ICookbookManagementService cookbookManagementService;
    private IUserCrudService userService;

    @Autowired
    public CookbookController( ICookbookManagementService cookbookManagementService, IUserCrudService userService ) {
        this.cookbookManagementService = cookbookManagementService;
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView showPublicCookbooks() {
        ModelAndView modelAndView = new ModelAndView( "PublicCookbooksTile" );

        List<Cookbook> cookbooks = this.cookbookManagementService.getPublicCookbooks()
                .stream()
                .map( cookbook -> new Cookbook( cookbook ) )
                .collect( Collectors.toList() );
        //List<Cookbook> cookbooks = new ArrayList<>();
        //
        //for ( int i = 1; i < 7; i++ ) {
        //    Cookbook cookbook = new Cookbook(  );
        //    cookbook.setId( i );
        //    cookbook.setName( "cookbook #" + i);
        //    cookbook.setVisibility( CookbookVisibility.PUBLIC );
        //    cookbook.setOwnerUsername( "dodo" );
        //    cookbook.setShortDescription( "A cool cookbook #" + i );
        //
        //    int k = new Random( System.currentTimeMillis() / i).nextInt( 13 ) + 1;
        //    List<Recipe> recipes = new ArrayList<>(  );
        //    for ( int j = 1; j < k; j++ ) {
        //        Recipe recipe = new Recipe(  );
        //        recipe.setName( "recipe #"+i+"."+j );
        //        recipes.add( recipe );
        //    }
        //    cookbook.setRecipes( recipes );
        //    cookbooks.add( cookbook );
        //}

        modelAndView.addObject( "cookbooks", cookbooks );

        return modelAndView;
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView managePersonalCookbooks() {
        ModelAndView modelAndView = new ModelAndView( "ManageCookbooksTile" );
        //int userId = this.userService.getCurrentUser().getId();
        //List<Cookbook> cookbooks = this.cookbookManagementService.getCookbooksForUser( userId )
        //        .stream()
        //        .map( cookbook -> new Cookbook( cookbook ) )
        //        .collect( Collectors.toList());
        List<Cookbook> cookbooks = new ArrayList<>();

        int k = new Random( 7897542 ).nextInt( 5 ) + 1;
        for ( int i = 1; i <= k; i++ ) {
            Cookbook cookbook = new Cookbook(  );
            cookbook.setId( i );
            cookbook.setName( "cookbook #" + i);
            cookbook.setVisibility( CookbookVisibility.PUBLIC );
            cookbook.setOwnerUsername( "dodo" );
            cookbook.setShortDescription( "A cool cookbook #" + i );
            cookbooks.add( cookbook );
        }

         k = new Random( 13134646 ).nextInt( 5 ) + 1;
        for ( int i = 1; i <= k; i++ ) {
            Cookbook cookbook = new Cookbook(  );
            cookbook.setId( i );
            cookbook.setName( "cookbook #" + i);
            cookbook.setVisibility( CookbookVisibility.FRIENDS );
            cookbook.setOwnerUsername( "dodo" );
            cookbook.setShortDescription( "A cool cookbook #" + i );
            cookbooks.add( cookbook );
        }

         k = new Random( 6487913 ).nextInt( 5 ) + 1;
        for ( int i = 1; i <= k; i++ ) {
            Cookbook cookbook = new Cookbook(  );
            cookbook.setId( i );
            cookbook.setName( "cookbook #" + i);
            cookbook.setVisibility( CookbookVisibility.PRIVATE );
            cookbook.setOwnerUsername( "dodo" );
            cookbook.setShortDescription( "A cool cookbook #" + i );
            cookbooks.add( cookbook );
        }



        CookbookOverview overview = new CookbookOverview( cookbooks );
        modelAndView.addObject( "overview", overview );

        return modelAndView;
    }
}
