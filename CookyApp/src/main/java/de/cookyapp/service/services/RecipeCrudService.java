package de.cookyapp.service.services;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.app.IIngredientCrudRepository;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;
import de.cookyapp.persistence.repositories.app.IRecipeIngredientCrudRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.service.dto.Cookbook;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.InvalidRecipeId;
import de.cookyapp.service.exceptions.UserNotAuthorized;
import de.cookyapp.service.services.interfaces.ICookbookContentService;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IImageService;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
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
    private IIngredientCrudRepository ingredientCrudRepository;
    private IRecipeIngredientCrudRepository recipeIngredientCrudRepository;
    private IAuthenticationFacade authentication;
    private IUserCrudRepository userCrudRepository;
    private ICookbookManagementService cookbookManagementService;
    private ICookbookContentService cookbookContentService;
    private IIngredientCrudService ingredientService;

    @Autowired
    private IImageService imageService;

    @Autowired
    public RecipeCrudService( IRecipeCrudRepository recipeCrudRepository, IAuthenticationFacade authentication,
                              IUserCrudRepository userCrudRepository, ICookbookManagementService cookbookManagementService,
                              ICookbookContentService cookbookContentService, IIngredientCrudService ingredientService,
                              IIngredientCrudRepository ingredientCrudRepository, IRecipeIngredientCrudRepository recipeIngredientCrudRepository ) {
        this.recipeCrudRepository = recipeCrudRepository;
        this.ingredientCrudRepository = ingredientCrudRepository;
        this.recipeIngredientCrudRepository = recipeIngredientCrudRepository;
        this.authentication = authentication;
        this.userCrudRepository = userCrudRepository;
        this.cookbookManagementService = cookbookManagementService;
        this.cookbookContentService = cookbookContentService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void deleteRecipe( int recipeID ) {
        RecipeEntity deleteRecipe = recipeCrudRepository.findOne( recipeID );
        if ( deleteRecipe != null ) {
            boolean isAuthorized = this.authentication.getAuthentication().getName().equals( deleteRecipe.getAuthor().getUsername() );

            if ( isAuthorized ) {
                recipeCrudRepository.delete( deleteRecipe );
            } else {
                throw new UserNotAuthorized();
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
            recipeEntity.setImageFile( recipe.getImageData() );
            recipeEntity = recipeCrudRepository.save( recipeEntity );

            this.ingredientService.saveRecipeIngredient( recipeEntity.getId(), recipe.getIngredients() );

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
                recipeEntity.setImageFile( recipe.getImageData() );
                recipeCrudRepository.save( recipeEntity );

                this.ingredientService.saveRecipeIngredient( recipeEntity.getId(), recipe.getIngredients() );
            }
        }
    }

    @Override
    public Recipe getRecipe( int recipeID ) {
        RecipeEntity recipeEntity = recipeCrudRepository.findOne( recipeID );
        Recipe recipe;
        if ( recipeEntity != null ) {
            recipe = new Recipe( recipeEntity );
            String imageUrl = getImageUrl( recipeEntity );
            recipe.setImageLink( imageUrl );
        } else {
            throw new InvalidRecipeId( recipeID );
        }
        return recipe;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        List<RecipeEntity> recipeEntities = recipeCrudRepository.findAll();
        List<Recipe> recipes = mapToRecipeList( recipeEntities );
        return recipes;
    }

    @Override
    public List<Recipe> getAllRecipesByName( String recipeName ) {
        List<RecipeEntity> recipeEntities = recipeCrudRepository.findByName( recipeName );
        List<Recipe> recipes = mapToRecipeList( recipeEntities );
        return recipes;
    }

    @Override
    public List<Recipe> searchRecipesContaining( String searchTerm ) {
        List<RecipeEntity> recipeEntities = recipeCrudRepository.findByNameContaining( searchTerm );
        List<Recipe> recipes = mapToRecipeList( recipeEntities );
        return recipes;
    }

    @Override
    public List<Recipe> recipeSuggestions( List<String> ingredientNames ) {
        List<Recipe> recipes = new ArrayList<>();
        if ( ingredientNames != null && ingredientNames.size() > 0 ) {
            List<String> names = ingredientNames.stream()
                    .distinct()
                    .filter( name -> !isBlank( name ) )
                    .collect( Collectors.toList() );

            List<IngredientEntity> ingredientEntities = new ArrayList<>();
            for ( String ingredientName : names ) {
                ingredientEntities.addAll( ingredientCrudRepository.findByNameContaining( ingredientName ) );
            }
            if ( names.size() > 0 && ingredientEntities.size() > 0 ) {
                List<RecipeIngredientEntity> recipeIngredientEntities = recipeIngredientCrudRepository.findRecipeIngredients( mapToIds( ingredientEntities ), names.size() );
                recipes = mapToRecipes( recipeIngredientEntities );
            }
        }
        return recipes;
    }

    private List<Recipe> mapToRecipes( List<RecipeIngredientEntity> entities ) {
        List<Recipe> recipes = new ArrayList<>( entities.size() );
        if ( entities != null ) {
            for ( RecipeIngredientEntity entity : entities ) {
                Recipe recipe = new Recipe( entity.getRecipe() );
                recipe.setImageLink( getImageUrl( entity.getRecipe() ) );
                if ( !recipes.contains( recipe ) ) {
                    recipes.add( recipe );
                }
            }
        }
        return recipes;
    }

    private List<Integer> mapToIds( List<IngredientEntity> ingredientEntities ) {
        List<Integer> ingredientIds = new ArrayList<>();
        if ( ingredientEntities != null ) {
            for ( IngredientEntity entity : ingredientEntities ) {
                ingredientIds.add( entity.getId() );
            }
        }
        return ingredientIds;
    }

    private List<Recipe> mapToRecipeList( List<RecipeEntity> entities ) {
        List<Recipe> recipes = new ArrayList<>();
        if ( entities != null ) {
            for ( RecipeEntity entity : entities ) {
                Recipe current = new Recipe( entity );
                String imageLink = getImageUrl( entity );
                current.setImageLink( imageLink );
                recipes.add( current );
            }
        }
        return recipes;
    }

    private String getImageUrl( RecipeEntity recipeEntity ) {
        String imageUrl;
        if ( recipeEntity.getImageFile() == null || this.imageService == null ) {
            imageUrl = "http://placehold.it/320x200";
        } else {
            try {
                imageUrl = this.imageService.writeImageThumbnail( recipeEntity.getImageFile(), new Dimension( 320, 200 ) );
            } catch ( IOException e ) {
                logger.error( "Image file could not be created. Recipe id: " + recipeEntity.getId() );
                imageUrl = "http://placehold.it/320x200";
            }
        }
        return imageUrl;
    }

    private boolean isBlank( String s ) {
        if ( s == null || (s.length() == 0) ) {
            return true;
        }

        for ( char c : s.toCharArray() ) {
            if ( !Character.isWhitespace( c ) ) {
                return false;
            }
        }

        return true;
    }
}
