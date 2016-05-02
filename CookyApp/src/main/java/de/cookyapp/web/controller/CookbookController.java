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
                view = "redirect:/cookbooks/manage";
            }
        }

        return view;
    }

    @RequestMapping( value = "/manage/delete/{cookbookId}", method = RequestMethod.POST )
    public String deleteCookbook( @PathVariable int cookbookId ) {
        String view = "redirect:/cookbooks/manage";

        this.cookbookManagementService.removeCookbook( cookbookId );

        return view;
    }

    @RequestMapping( value = "/view/{cookbookId}" )
    public ModelAndView viewCookbookDetails( @PathVariable int cookbookId ) {
        ModelAndView modelAndView = new ModelAndView( "CookbookDetailTile" );

        de.cookyapp.service.dto.Cookbook cookbookDTO = this.cookbookManagementService.getCookbook( cookbookId );
        Cookbook cookbook = new Cookbook( cookbookDTO );
        modelAndView.addObject( "cookbook", cookbook );

        return modelAndView;
    }
}
