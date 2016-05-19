package de.cookyapp.service.services;

import java.util.List;

import javax.servlet.ServletContext;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.services.interfaces.IRecipeOfTheMonthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mario on 16.05.2016.
 */
@Transactional
@Service
public class RecipeOfTheMonthService implements IRecipeOfTheMonthService {
    private Logger logger = Logger.getLogger( RecipeOfTheMonthService.class );

    private IRecipeCrudRepository recipeCrudRepository;
    private IAuthenticationFacade authentication;
    private IUserCrudRepository userCrudRepository;
    private ServletContext servletContext;
    private RecipeCrudService recipeCrudService;

    @Autowired
    public RecipeOfTheMonthService( IRecipeCrudRepository recipeCrudRepository, IAuthenticationFacade authentication, IUserCrudRepository userCrudRepository, ServletContext servletContext, RecipeCrudService recipeCrudService ) {
        this.recipeCrudRepository = recipeCrudRepository;
        this.authentication = authentication;
        this.userCrudRepository = userCrudRepository;
        this.servletContext = servletContext;
        this.recipeCrudService = recipeCrudService;
    }

    @Override
    public List<Recipe> getRecipeOfTheMonth() {

    }

    @Override
    public List<Recipe> updateRecipeOfTheMonth() {
        List<RecipeEntity> recipeEntities = recipeCrudRepository.findTop10ByOrderByRatingDescVoteCountDesc();
        List<Recipe> recipes = recipeCrudService.recipeEntityListToRecipeList( recipeEntities );


        return recipes;
    }


}
