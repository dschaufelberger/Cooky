package de.cookyapp.web.controller;

import de.cookyapp.enums.SearchType;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Mario on 24.05.2016.
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    private Logger logger = Logger.getLogger( RecipeController.class );

    @ModelAttribute
    public void getSearchTypes( Model model ) {
        model.addAttribute( "availableSearchTypes", SearchType.values() );
    }


}