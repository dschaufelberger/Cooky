package de.cookyapp.service.services;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.repositories.IRecipeCrudRepository;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.exceptions.InvalidRecipeID;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
@Transactional
@Service
public class RecipeCrudService implements IRecipeCrudService {
    private IRecipeCrudRepository recipeCrudRepository;
    private IAuthenticationFacade authentication;

    @Autowired
    public RecipeCrudService( IRecipeCrudRepository recipeCrudRepository, IAuthenticationFacade authentication ) {
        this.recipeCrudRepository = recipeCrudRepository;
        this.authentication = authentication;
    }

    @Override
    public void deleteRecipe(int recipeID) throws InvalidRecipeID {
        RecipeEntity deleteRecipe = recipeCrudRepository.findOne( recipeID );
        if ( deleteRecipe != null ) {
            boolean isAuthorized = this.authentication.getAuthentication().getName().equals( deleteRecipe.getAuthor().getUsername() ); //Check current User Authentication
            //TODO if (!isAuthorized) --> Check if Admin delete Recipe
            if ( isAuthorized ) {
                recipeCrudRepository.delete( deleteRecipe );
            }
        }

    }

    @Override
    public Recipe createRecipe( Recipe recipe ) {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setAuthor( recipe.getAuthor() );
        recipeEntity.setName( recipe.getName() );
        recipeEntity.setRating( recipe.getRating() );
        recipeEntity.setServing( recipe.getServing() );
        recipeEntity.setCalories( recipe.getCalories() );
        recipeEntity.setAuthor( recipe.getAuthor() );
        recipeEntity.setDifficulty( recipe.getDifficulty() );
        recipeEntity.setImageFileName( recipe.getImageFileName() );
        recipeEntity.setShortDescription( recipe.getShortDescription() );
        recipeEntity.setCreationTime( LocalDateTime.now() );
        recipeEntity.setWorkingTime( recipe.getWorkingTime() );
        recipeEntity.setPreparation( recipe.getPreparation() );
        recipeEntity.setCookingTime( recipe.getCookingTime() );
        recipeEntity.setRestTime( recipe.getRestTime() );

        recipeEntity = recipeCrudRepository.save( recipeEntity );

        return new Recipe( recipeEntity );
    }

    @Override
    public void updateRecipe( Recipe recipe ) {
        if ( recipe != null ) {
            RecipeEntity recipeEntity = recipeCrudRepository.findOne( recipe.getId() );
            boolean isAuthenticated = authentication.getAuthentication().getName().equals( recipe.getAuthor().getUsername() );
            if ( isAuthenticated ) {
                recipeEntity.setName( recipe.getName() );
                recipeEntity.setWorkingTime( recipe.getWorkingTime() );
                recipeEntity.setRestTime( recipe.getRestTime() );
                recipeEntity.setShortDescription( recipe.getShortDescription() );
                recipeEntity.setCalories( recipe.getCalories() );
                recipeEntity.setDifficulty( recipe.getDifficulty() );
                recipeEntity.setCookingTime( recipe.getCookingTime() );
                recipeEntity.setImageFileName( recipe.getImageFileName() );
                recipeEntity.setPreparation( recipe.getPreparation() );
                recipeEntity.setServing( recipe.getServing() );
                recipeEntity.setAuthor( recipe.getAuthor() );
                recipeCrudRepository.save( recipeEntity );
            }
        }
    }

    @Override
    public Recipe getRecipe( int recipeID ) {
        RecipeEntity recipeEntity = recipeCrudRepository.findOne( recipeID );
        Recipe recipe = null;
        if ( recipeEntity != null ) {
            recipe = new Recipe( recipeEntity );
        }
        return recipe;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<RecipeEntity> recipeEntities = recipeCrudRepository.findAll();
        List<Recipe> recipes = recipeEntityListToRecipeList( recipeEntities );
        return recipes;
    }

    @Override
    public List<Recipe> getAllRecipesByName( String recipeName ) {
        List<RecipeEntity> recipeEntities = recipeCrudRepository.findByName( recipeName );
        List<Recipe> recipes = recipeEntityListToRecipeList( recipeEntities );
        return recipes;
    }

    @Override
    public List<Recipe> searchRecipesContaining( String searchTerm ) {
        List<RecipeEntity> recipeEntities = recipeCrudRepository.findByNameLike( searchTerm );
        List<Recipe> recipes = recipeEntityListToRecipeList( recipeEntities );
        return recipes;
    }

    private List<Recipe> recipeEntityListToRecipeList( List<RecipeEntity> entities ) {
        List<Recipe> recipes = new ArrayList<>();
        if ( entities != null ) {
            for ( RecipeEntity entity : entities ) {
                Recipe current = new Recipe( entity );
                recipes.add( current );
            }
        }
        return recipes;
    }
}
