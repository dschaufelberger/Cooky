package de.cookyapp.service.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeOfTheMonthEntity;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;
import de.cookyapp.persistence.repositories.app.IRecipeOfTheMonthRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
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
    private IRecipeOfTheMonthRepository recipeOfTheMonthRepository;

    @Autowired
    public RecipeOfTheMonthService( IRecipeOfTheMonthRepository recipeOfTheMonthRepository, IRecipeCrudRepository recipeCrudRepository, IAuthenticationFacade authentication, IUserCrudRepository userCrudRepository, ServletContext servletContext) {
        this.recipeCrudRepository = recipeCrudRepository;
        this.authentication = authentication;
        this.userCrudRepository = userCrudRepository;
        this.servletContext = servletContext;
        this.recipeOfTheMonthRepository = recipeOfTheMonthRepository;
    }

    @Override
    public Recipe getRecipeOfTheMonth() {
        Recipe recipe = null;
        Calendar now = Calendar.getInstance();
        RecipeOfTheMonthEntity recipeOfTheMonthEntity = recipeOfTheMonthRepository.findFirstByOrderByUpdatedDesc();
        if(recipeOfTheMonthEntity != null) {
            // month start from 0 to 11
            if ( recipeOfTheMonthEntity.getUpdated().getMonthValue() < (now.get( Calendar.MONTH ) + 1) ) {
                RecipeEntity recipeEntity = recipeCrudRepository.findTopByOrderByRatingDesc();
                RecipeOfTheMonthEntity newRecipeOfTheMonthEntity = null;
                newRecipeOfTheMonthEntity.setRecipe( recipeEntity );
                newRecipeOfTheMonthEntity.setUpdated( LocalDate.now() );
                recipeOfTheMonthEntity = recipeOfTheMonthRepository.save( newRecipeOfTheMonthEntity );
            }
            if ( recipeOfTheMonthEntity.getRecipe() != null && recipeOfTheMonthEntity != null  ) {
                RecipeEntity entity = recipeOfTheMonthEntity.getRecipe();
                recipe = new Recipe( entity );
            }
        }
        return recipe;
    }

    @Override
    public List<RecipeOfTheMonthEntity> getAllRecipesOfTheMonth() {
        List<RecipeOfTheMonthEntity> recipeOfTheMonthEntities = recipeOfTheMonthRepository.findAll();
        return recipeOfTheMonthEntities;
    }
}