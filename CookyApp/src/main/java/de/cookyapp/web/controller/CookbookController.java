package de.cookyapp.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.cookbook.Cookbook;
import de.cookyapp.web.viewmodel.cookbook.CookbookOverview;
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
    private ICookbookManagementService cookbookManagementService;
    private IUserCrudService userService;
    private Logger logger = Logger.getLogger( CookbookController.class );

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

    @RequestMapping( value = "/manage", method = RequestMethod.GET )
    public ModelAndView managePersonalCookbooks() {
        ModelAndView modelAndView = new ModelAndView( "ManageCookbooksTile" );
        int userId = this.userService.getCurrentUser().getId();
        List<Cookbook> cookbooks = this.cookbookManagementService.getCookbooksForUser( userId )
                .stream()
                .map( cookbook -> new Cookbook( cookbook ) )
                .collect( Collectors.toList() );
        //List<Cookbook> cookbooks = new ArrayList<>();
        //
        //int k = new Random( 7897542 ).nextInt( 5 ) + 1;
        //for ( int i = 1; i <= k; i++ ) {
        //    Cookbook cookbook = new Cookbook();
        //    cookbook.setId( i );
        //    cookbook.setName( "public cookbook #" + i );
        //    cookbook.setVisibility( CookbookVisibility.PUBLIC );
        //    cookbook.setOwnerUsername( "dodo" );
        //    cookbook.setShortDescription( "A cool public cookbook #" + i );
        //    cookbooks.add( cookbook );
        //}
        //
        //k = new Random( 13134646 ).nextInt( 5 ) + 1;
        //for ( int i = 1; i <= k; i++ ) {
        //    Cookbook cookbook = new Cookbook();
        //    cookbook.setId( i );
        //    cookbook.setName( "shared cookbook #" + i );
        //    cookbook.setVisibility( CookbookVisibility.FRIENDS );
        //    cookbook.setOwnerUsername( "dodo" );
        //    cookbook.setShortDescription( "A cool shared cookbook #" + i );
        //    cookbooks.add( cookbook );
        //}
        //
        //k = new Random( 6487913 ).nextInt( 5 ) + 1;
        //for ( int i = 1; i <= k; i++ ) {
        //    Cookbook cookbook = new Cookbook();
        //    cookbook.setId( i );
        //    cookbook.setName( "private cookbook #" + i );
        //    cookbook.setVisibility( CookbookVisibility.PRIVATE );
        //    cookbook.setOwnerUsername( "dodo" );
        //    cookbook.setShortDescription( "A cool private cookbook #" + i );
        //    cookbooks.add( cookbook );
        //}


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
            this.cookbookManagementService.saveCookbook( cookbookDTO );
            view = "redirect:/cookbooks/manage";
        }

        this.logger.debug( "New name is: " + cookbook.getName() );
        this.logger.debug( "New description is: " + cookbook.getShortDescription() );
        this.logger.debug( "New visibility is: " + cookbook.getVisibility().toString() );

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
                this.cookbookManagementService.createCookbookForUser( user.getId(), cookbookDTO );
            }

            view = "redirect:/cookbooks/manage";
        }

        this.logger.debug( "New name is: " + cookbook.getName() );
        this.logger.debug( "New description is: " + cookbook.getShortDescription() );
        this.logger.debug( "New visibility is: " + cookbook.getVisibility() );

        return view;
    }

    @RequestMapping( value = "/manage/delete/{cookbookID}", method = RequestMethod.POST )
    public String deleteCookbook( @PathVariable int cookbookID ) {
        String view = "redirect:/cookbooks/manage";

        this.cookbookManagementService.removeCookbook( cookbookID );

        return view;
    }
}
