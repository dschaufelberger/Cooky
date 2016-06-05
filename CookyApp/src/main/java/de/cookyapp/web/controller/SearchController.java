package de.cookyapp.web.controller;

import java.util.List;
import javax.validation.Valid;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.enums.SearchType;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.Search;
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
    private IAuthenticationFacade authentication;
    private Logger logger = Logger.getLogger( SearchController.class );

    @Autowired
    public SearchController(IRecipeCrudService recipeCrudService, IUserCrudService userCrudService, IAuthenticationFacade authenticationFacade) {
        this.recipeCrudService = recipeCrudService;
        this.authentication = authenticationFacade;
        this.userCrudService = userCrudService;
    }

    @RequestMapping( method = RequestMethod.POST )
        public ModelAndView search( @ModelAttribute( "search" ) @Valid Search search ) {
            ModelAndView modelAndView;
            List<User> userList;
            SearchType searchType = search.getSearchType();
            if(searchType == SearchType.USERS) {
                modelAndView = new ModelAndView( "UserOverviewTile" );
                userList= userCrudService.searchUsersContaining( search.getSearchQuery());
                modelAndView.addObject( "usersList", userList );
            }else {
                modelAndView = new ModelAndView( "RecipeOverviewTile" );
                modelAndView.addObject( "recipesList", recipeCrudService.searchRecipesContaining( search.getSearchQuery() ) );
            }
        return modelAndView;
        }
}