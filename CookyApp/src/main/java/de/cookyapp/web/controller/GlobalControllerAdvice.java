package de.cookyapp.web.controller;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IImageUploadService;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import de.cookyapp.service.services.interfaces.IRecipeOfTheMonthService;
import de.cookyapp.service.services.interfaces.IRecipeRatingService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Mario on 24.05.2016.
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    private Logger logger = Logger.getLogger( RecipeController.class );
    private IRecipeOfTheMonthService recipeOfTheMonthService;

    @Autowired
    public GlobalControllerAdvice( IRecipeOfTheMonthService recipeOfTheMonthService) {
        this.recipeOfTheMonthService = recipeOfTheMonthService;
    }

    @ModelAttribute
    public ModelAndView showRecipeOfTheMonth( ) {
        ModelAndView model = new ModelAndView( "RecipeOfTheMonthTile" );
        model.addObject( "recipeOfTheMonth", this.recipeOfTheMonthService.getRecipeOfTheMonth() );
        return model;
    }

}