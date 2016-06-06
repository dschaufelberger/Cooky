package de.cookyapp.service.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import de.cookyapp.persistence.repositories.app.IIngredientCrudRepository;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;
import de.cookyapp.persistence.repositories.app.IRecipeIngredientCrudRepository;
import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.exceptions.InvalidRecipeId;
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

    @Autowired
    public IngredientCrudService( IIngredientCrudRepository ingredientCrudRepository, IRecipeIngredientCrudRepository recipeIngredientCrudRepository, IRecipeCrudRepository recipeCrudRepository ) {
        this.ingredientCrudRepository = ingredientCrudRepository;
        this.recipeIngredientCrudRepository = recipeIngredientCrudRepository;
        this.recipeCrudRepository = recipeCrudRepository;
    }

    @Override
    public void saveRecipeIngredient( int recipeId, List<Ingredient> ingredients ) {
        LinkedList<RecipeIngredientEntity> mappingEntities = new LinkedList<>();
        RecipeEntity recipe = this.recipeCrudRepository.findOne( recipeId );

        if ( recipe == null ) {
            throw new InvalidRecipeId( recipeId );
        } else {
            LinkedList<IngredientEntity> ingredientsToMap = new LinkedList<>();

            for ( Ingredient ingredient : ingredients ) {
                if ( ingredient != null && !isBlank( ingredient.getName() ) ) {
                    IngredientEntity ingredientEntity = this.ingredientCrudRepository.findFirstByName( ingredient.getName() );

                    // ingredient with name does not exit
                    if ( ingredientEntity == null ) {
                        IngredientEntity newEntity = new IngredientEntity();
                        newEntity.setName( ingredient.getName() );
                        ingredientEntity = this.ingredientCrudRepository.save( newEntity );
                    } else {
                        ingredientsToMap.add( ingredientEntity );
                    }

                    RecipeIngredientEntity mappingEntity = new RecipeIngredientEntity();
                    mappingEntity.setRecipe( recipe );
                    mappingEntity.setIngredient( ingredientEntity );
                    mappingEntity.setAmount( ingredient.getAmount() );
                    mappingEntity.setUnit( ingredient.getUnit() );
                    mappingEntities.add( mappingEntity );
                }
            }

            List<RecipeIngredientEntity> allMappings = this.recipeIngredientCrudRepository.findByRecipeId( recipeId );
            this.recipeIngredientCrudRepository.delete( allMappings );
            this.recipeIngredientCrudRepository.save( mappingEntities );
        }
    }

    @Override
    public List<Ingredient> loadRecipeIngredients( int recipeId ) {
        List<RecipeIngredientEntity> recipeIngredientEntities = recipeIngredientCrudRepository.findByRecipeId( recipeId );
        List<Ingredient> ingredientList = new ArrayList<>( recipeIngredientEntities.size() );

        for ( RecipeIngredientEntity current : recipeIngredientEntities ) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId( current.getIngredient().getId() );
            ingredient.setName( current.getIngredient().getName() );
            ingredient.setUnit( current.getUnit() );
            ingredient.setAmount( current.getAmount() );

            ingredientList.add( ingredient );
        }

        return ingredientList;
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