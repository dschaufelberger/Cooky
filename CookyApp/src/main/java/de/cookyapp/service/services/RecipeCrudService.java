package de.cookyapp.service.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.service.dto.Cookbook;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.ICookbookContentService;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
@Transactional
@Service
public class RecipeCrudService implements IRecipeCrudService {
    private Logger logger = Logger.getLogger( RecipeCrudService.class );

    private IRecipeCrudRepository recipeCrudRepository;
    private IAuthenticationFacade authentication;
    private IUserCrudRepository userCrudRepository;
    private ServletContext servletContext;
    private ICookbookManagementService cookbookManagementService;
    private ICookbookContentService cookbookContentService;

    @Autowired
    public RecipeCrudService( IRecipeCrudRepository recipeCrudRepository, IAuthenticationFacade authentication,
                              IUserCrudRepository userCrudRepository, ServletContext servletContext,
                              ICookbookManagementService cookbookManagementService, ICookbookContentService cookbookContentService ) {
        this.recipeCrudRepository = recipeCrudRepository;
        this.authentication = authentication;
        this.userCrudRepository = userCrudRepository;
        this.servletContext = servletContext;
        this.cookbookManagementService = cookbookManagementService;
        this.cookbookContentService = cookbookContentService;
    }

    @Override
    public void deleteRecipe( int recipeID ) {
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
        UserEntity user = this.userCrudRepository.findByUsername( this.authentication.getAuthentication().getName() );

        if ( user != null ) {
            RecipeEntity recipeEntity = new RecipeEntity();
            recipeEntity.setName( recipe.getName() );
            recipeEntity.setRating( recipe.getRating() );
            recipeEntity.setServing( recipe.getServing() );
            recipeEntity.setCalories( recipe.getCalories() );
            recipeEntity.setDifficulty( recipe.getDifficulty() );
            recipeEntity.setShortDescription( recipe.getShortDescription() );
            recipeEntity.setCreationTime( LocalDateTime.now() );
            recipeEntity.setWorkingTime( recipe.getWorkingTime() );
            recipeEntity.setPreparation( recipe.getPreparation() );
            recipeEntity.setCookingTime( recipe.getCookingTime() );
            recipeEntity.setRestTime( recipe.getRestTime() );
            recipeEntity.setRating( (byte) 0 );
            recipeEntity.setVoteCount( 0 );
            recipeEntity.setAuthor( user );

            recipeEntity = recipeCrudRepository.save( recipeEntity );

            Cookbook defaultCookbook = this.cookbookManagementService.getDefaultCookbookForUser( user.getId() );

            if ( defaultCookbook == null ) {
                defaultCookbook = this.cookbookManagementService.createDefaultCookbookForUser( new User( user ) );
            }

            this.cookbookContentService.addRecipeToDefaultCookbook( defaultCookbook.getId(), recipeEntity.getId() );

            return new Recipe( recipeEntity );
        }

        return null;
    }

    @Override
    public void updateRecipe( Recipe recipe ) {
        if ( recipe != null ) {
            RecipeEntity recipeEntity = recipeCrudRepository.findOne( recipe.getId() );
            boolean isAuthenticated = authentication.getAuthentication().getName().equals( recipeEntity.getAuthor().getUsername() );
            if ( isAuthenticated ) {
                recipeEntity.setName( recipe.getName() );
                recipeEntity.setWorkingTime( recipe.getWorkingTime() );
                recipeEntity.setRestTime( recipe.getRestTime() );
                recipeEntity.setShortDescription( recipe.getShortDescription() );
                recipeEntity.setCalories( recipe.getCalories() );
                recipeEntity.setDifficulty( recipe.getDifficulty() );
                recipeEntity.setCookingTime( recipe.getCookingTime() );
                recipeEntity.setPreparation( recipe.getPreparation() );
                recipeEntity.setServing( recipe.getServing() );
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
        List<RecipeEntity> recipeEntities = recipeCrudRepository.findByNameContaining( searchTerm );
        List<Recipe> recipes = recipeEntityListToRecipeList( recipeEntities );
        return recipes;
    }

    private List<Recipe> recipeEntityListToRecipeList( List<RecipeEntity> entities ) {
        List<Recipe> recipes = new ArrayList<>();
        if ( entities != null ) {
            for ( RecipeEntity entity : entities ) {
                Recipe current = new Recipe( entity );
                if ( entity.getImageFile() == null ) {
                    current.setImageLink( "http://placehold.it/320x200" );
                } else {
                    current.setImageLink( byteArrayToFileLink( entity.getImageFile() ) );
                }
                recipes.add( current );
            }
        }
        return recipes;
    }

    private String byteArrayToFileLink( byte[] bytes ) {
        String imageGUID = java.util.UUID.randomUUID().toString() + ".jpg";
        String path = generatePath();
        String completePath = path + imageGUID;
        String imagePath = "/resources/images/recipes/" + imageGUID;
        InputStream inputStream = new ByteArrayInputStream( bytes );
        try {
            BufferedImage bufferedImage = ImageIO.read( inputStream );
            ImageIO.write( bufferedImage, "jpg", new File( completePath ) );
        } catch ( IOException ex ) {
            logger.error( ex.getMessage(), ex );
        }

        return imagePath;
    }

    private String generatePath() {
        String path;
        String imagePath = "resources/images/recipes/";
        path = this.servletContext.getRealPath( "/" ) + imagePath;
        if ( !new File( path ).exists() ) {
            File file = new File( path );
            file.mkdirs();
        }
        return path;
    }
}
