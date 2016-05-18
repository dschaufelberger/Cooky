package de.cookyapp.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.ICookbookContentService;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.RecipeCookbook;
import de.cookyapp.web.viewmodel.cookbook.Cookbook;
import de.cookyapp.web.viewmodel.cookbook.CookbookOverview;
import de.cookyapp.web.viewmodel.cookbook.Recipe;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dominik Schaufelberger on 22.04.2016.
 */
@Controller
@RequestMapping( "/cookbooks" )
public class CookbookController {
    private ICookbookManagementService managementService;
    private ICookbookContentService contentService;
    private IUserCrudService userService;
    private Logger logger = Logger.getLogger( CookbookController.class );

    @Autowired
    public CookbookController( ICookbookManagementService managementService, ICookbookContentService contentService, IUserCrudService userService ) {
        this.managementService = managementService;
        this.contentService = contentService;
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView showPublicCookbooks() {
        ModelAndView modelAndView = new ModelAndView( "PublicCookbooksTile" );

        List<Cookbook> cookbooks = this.managementService.getPublicCookbooks()
                .stream()
                .map( cookbook -> new Cookbook( cookbook ) )
                .collect( Collectors.toList() );

        modelAndView.addObject( "cookbooks", cookbooks );

        return modelAndView;
    }

    @RequestMapping( value = "/manage", method = RequestMethod.GET )
    public ModelAndView managePersonalCookbooks() {
        ModelAndView modelAndView = new ModelAndView( "ManageCookbooksTile" );
        int userId = this.userService.getCurrentUser().getId();
        List<Cookbook> cookbooks = this.managementService.getCookbooksForUser( userId )
                .stream()
                .map( cookbook -> new Cookbook( cookbook ) )
                .collect( Collectors.toList() );

        CookbookOverview overview = new CookbookOverview( cookbooks );
        modelAndView.addObject( "overview", overview );
        modelAndView.addObject( "cookbook", new Cookbook() );
        modelAndView.addObject( "newCookbook", new Cookbook() );

        return modelAndView;
    }

    @RequestMapping( value = "/manage/save", method = RequestMethod.POST )
    public String saveCookbook( @Valid Cookbook cookbook, BindingResult bindingResult ) {
        String view;

        if ( bindingResult.hasErrors() ) {
            view = "ManageCookbooksTile";
        } else {
            de.cookyapp.service.dto.Cookbook cookbookDTO = new de.cookyapp.service.dto.Cookbook();
            cookbookDTO.setId( cookbook.getId() );
            cookbookDTO.setName( cookbook.getName() );
            cookbookDTO.setShortDescription( cookbook.getShortDescription() );
            cookbookDTO.setVisibility( cookbook.getVisibility() );
            this.managementService.saveCookbook( cookbookDTO );
            view = "redirect:/cookbooks/manage";
        }

        return view;
    }

    @RequestMapping( value = "/manage/create", method = RequestMethod.POST )
    public String createCookbook( @ModelAttribute( "newCookbook" ) @Valid Cookbook cookbook, BindingResult bindingResult ) {
        String view;

        if ( bindingResult.hasErrors() ) {
            view = "ManageCookbooksTile";
        } else {
            User user = this.userService.getCurrentUser();

            if ( user == null ) {
                view = "redirect:/signin";
            } else {
                de.cookyapp.service.dto.Cookbook cookbookDTO = new de.cookyapp.service.dto.Cookbook();
                cookbookDTO.setName( cookbook.getName() );
                cookbookDTO.setShortDescription( cookbook.getShortDescription() );
                cookbookDTO.setVisibility( cookbook.getVisibility() );
                this.managementService.createCookbookForUser( user.getId(), cookbookDTO );
                view = "redirect:/cookbooks/manage";
            }
        }

        return view;
    }

    @RequestMapping( value = "/manage/delete/{cookbookId}", method = RequestMethod.POST )
    public String deleteCookbook( @PathVariable int cookbookId ) {
        String view = "redirect:/cookbooks/manage";

        this.managementService.removeCookbook( cookbookId );

        return view;
    }

    @RequestMapping( value = "/view/{cookbookId}" )
    public ModelAndView viewCookbookDetails( @PathVariable int cookbookId ) {
        ModelAndView modelAndView = new ModelAndView( "CookbookDetailTile" );

        de.cookyapp.service.dto.Cookbook cookbookDTO = this.managementService.getCookbook( cookbookId );
        User user = this.userService.getCurrentUser();
        Cookbook cookbook = new Cookbook( cookbookDTO );
        Recipe recipe = new Recipe();
        recipe.setContainingCookbook( cookbook );

        List<Cookbook> cookbooks;
        if ( user == null ) {
            cookbooks = new LinkedList<>();
        } else {
            cookbooks = this.managementService.getCookbooksForUser( user.getId() )
                    .stream()
                    .map( cb -> new Cookbook( cb ) )
                    .collect( Collectors.toList() );
        }
        CookbookOverview cookbookOverview = new CookbookOverview( cookbooks );

        modelAndView.addObject( "cookbook", cookbook );
        modelAndView.addObject( "recipe", recipe );
        modelAndView.addObject( "cookbookOverview", cookbookOverview );

        return modelAndView;
    }

    @RequestMapping( value = "/addRecipe" )
    public String addRecipeToCookbook( RecipeCookbook cookbook ) {
        this.contentService.addRecipeToCookbook( cookbook.getCookbookId(), cookbook.getRecipeId() );

        return "redirect:/recipes/view/" + cookbook.getRecipeId();
    }

    @RequestMapping( value = "/removeRecipe" )
    public String removeRecipeFromCookbook( Recipe recipe ) {
        int recipeId = recipe.getId();
        int containigCookbookId = recipe.getContainingCookbook().getId();

        this.contentService.removeRecipeFromCookbook( containigCookbookId, recipeId );

        return "redirect:/cookbooks/view/" + containigCookbookId;
    }

    @RequestMapping( value = "/moveRecipe" )
    public String moveRecipeToAnotherCookbook( Recipe recipe ) {
        int recipeId = recipe.getId();
        int currentCookbookId = recipe.getContainingCookbook().getId();
        int newCookbookId = recipe.getMovedToCookbook().getId();

        if ( currentCookbookId != newCookbookId ) {
            this.contentService.moveRecipeBetweenCookbooks( recipeId, currentCookbookId, newCookbookId );
        }

        return "redirect:/cookbooks/view/" + currentCookbookId;
    }
}
