package de.cookyapp.service.services;

import java.util.ArrayList;
import java.util.List;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.authentication.IUserAuthorization;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import de.cookyapp.persistence.repositories.IIngredientCrudRepository;
import de.cookyapp.persistence.repositories.IRecipeCrudRepository;
import de.cookyapp.persistence.repositories.IRecipeIngredientCrudRepository;
import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jasper on 12.04.2016.
 */
@Transactional
@Service
public class IngredientCrudService implements IIngredientCrudService {
    private IIngredientCrudRepository ingredientCrudRepository;
    private IRecipeIngredientCrudRepository recipeIngredientCrudRepository;
    private IRecipeCrudRepository recipeCrudRepository;
    private IAuthenticationFacade authentication;
    private IUserAuthorization userAuthorization;

    @Autowired
    public IngredientCrudService( IIngredientCrudRepository ingredientCrudRepository, IAuthenticationFacade authentication, IUserAuthorization authorization, IRecipeIngredientCrudRepository recipeIngredientCrudRepository, IRecipeCrudRepository recipeCrudRepository ) {
        this.ingredientCrudRepository = ingredientCrudRepository;
        this.recipeIngredientCrudRepository = recipeIngredientCrudRepository;
        this.recipeCrudRepository = recipeCrudRepository;
        this.authentication = authentication;
        this.userAuthorization = authorization;
    }

    @Override
    public void deleteIngredient( int ingredientId ) {
        deleteIngredientById( ingredientId );
    }

    @Override
    public void addIngredient( Ingredient ingredient ) {
        add( ingredient );
    }

    @Override
    public void updateIngredient( Ingredient ingredient ) {
        if ( ingredient != null ) {
            if ( ingredientCrudRepository.findFirstByName( ingredient.getName() ) == null ) {
                add( ingredient );
            }
        }
    }

    @Override
    public Ingredient getIngredient( int ingredientId ) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName( ingredientCrudRepository.findOne( ingredientId ).getName() );
        ingredient.setAmount( recipeIngredientCrudRepository.findOne( ingredientId ).getAmount() );
        ingredient.setUnit( recipeIngredientCrudRepository.findOne( ingredientId ).getUnit() );

        return ingredient;
    }

    @Override
    public void save( List<Ingredient> ingredients ) {
        for ( Ingredient ingredient : ingredients ) {
            if ( ingredientCrudRepository.findFirstByName( ingredient.getName() ) != null ) {

            } else {
                IngredientEntity ingredientEntity = new IngredientEntity();
                ingredientEntity.setName( ingredient.getName() );
                ingredientCrudRepository.save( ingredientEntity );
            }
        }
    }

    @Override
    public void saveRecipeIngredient( int recipeId, List<Ingredient> ingredients ) {
        List<RecipeIngredientEntity> recipeIngredientEntities = recipeIngredientCrudRepository.findByRecipeId( recipeId );
        RecipeEntity recipe = recipeCrudRepository.findOne( recipeId );
        int counter = 0;
        for ( Ingredient ingredient : ingredients ) {
            RecipeIngredientEntity recipeIngredientEntity = null;
            IngredientEntity ingredientEntity = ingredientCrudRepository.findFirstByName( ingredient.getName() );

            if ( recipeIngredientEntities.size() > 0 ) {
                if ( recipeIngredientEntities.get( counter ) != null && recipeIngredientEntities.get( counter ).getIngredient().getId() == ingredientEntity.getId() ) {
                    recipeIngredientEntity = recipeIngredientEntities.get( counter );
                    recipeIngredientEntity.getIngredient().setName( ingredientEntity.getName() );
                }
            }

            if ( recipeIngredientEntity == null ) {
                recipeIngredientEntity = new RecipeIngredientEntity();
                recipeIngredientEntity.setAmount( ingredient.getAmount() );
                recipeIngredientEntity.setUnit( ingredient.getUnit() );
                recipeIngredientEntity.setRecipe( recipe );
                recipeIngredientEntity.setIngredient( ingredientEntity );
            }

            recipeIngredientCrudRepository.save( recipeIngredientEntity );
            counter++;
        }
    }

    @Override
    public List<Ingredient> loadRecipeIngredients( int recipeId ) {
        List<RecipeIngredientEntity> recipeIngredientEntities = recipeIngredientCrudRepository.findByRecipeId( recipeId );
        List<Ingredient> ingredientList = new ArrayList<>();
        for ( RecipeIngredientEntity current : recipeIngredientEntities ) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId( current.getId() );
            ingredient.setName( current.getIngredient().getName() );
            ingredient.setUnit( current.getUnit() );
            ingredient.setAmount( current.getAmount() );

            ingredientList.add( ingredient );
        }
        return ingredientList;
    }

    private void deleteIngredientById( int id ) {
        List<RecipeIngredientEntity> recipeIngredientEntities = recipeIngredientCrudRepository.findByIngredientId( id );
        if ( recipeIngredientEntities.size() == 0 ) {
            IngredientEntity ingredientEntity = this.ingredientCrudRepository.findOne( id );
            this.ingredientCrudRepository.delete( ingredientEntity );
        }
    }

    private void add( Ingredient ingredient ) {
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setName( ingredient.getName() );
        ingredientCrudRepository.save( ingredientEntity );
    }
}