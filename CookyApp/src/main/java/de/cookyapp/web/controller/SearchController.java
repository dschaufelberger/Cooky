package de.cookyapp.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import de.cookyapp.enums.SearchType;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.recipes.Recipe;
import de.cookyapp.web.viewmodel.recipes.Search;
import de.cookyapp.web.viewmodel.search.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mario on 29.05.2016.
 */
@Controller
@RequestMapping( "/search" )
public class SearchController {
    private IRecipeCrudService recipeCrudService;
    private IUserCrudService userCrudService;
    private Logger logger = Logger.getLogger( SearchController.class );

    @Autowired
    public SearchController( IRecipeCrudService recipeCrudService, IUserCrudService userCrudService ) {
        this.recipeCrudService = recipeCrudService;
        this.userCrudService = userCrudService;
    }

    @RequestMapping( method = RequestMethod.POST )
    public ModelAndView search( @ModelAttribute( "search" ) @Valid Search search ) {
        ModelAndView modelAndView;
        SearchType searchType = search.getSearchType();
        if ( searchType == SearchType.USERS ) {
            modelAndView = new ModelAndView( "UserOverviewTile" );

            List<de.cookyapp.service.dto.User> users = userCrudService.searchUsersContaining( search.getSearchQuery() );
            List<User> userList = users.stream()
                    .map( user -> new User( user ) )
                    .collect( Collectors.toList() );

            modelAndView.addObject( "usersList", userList );
        } else {
            modelAndView = new ModelAndView( "RecipeOverviewTile" );

            List<de.cookyapp.service.dto.Recipe> recipes = recipeCrudService.searchRecipesContaining( search.getSearchQuery() );
            List<Recipe> recipeList = recipes.stream()
                    .map( recipe -> new Recipe( recipe ) )
                    .collect( Collectors.toList() );

            modelAndView.addObject( "recipesList", recipeList );
        }
        return modelAndView;
    }
}
