package de.cookyapp.service.services;

import java.util.ArrayList;
import java.util.List;

import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import de.cookyapp.persistence.repositories.IIngredientCrudRepository;
import de.cookyapp.persistence.repositories.IRecipeCrudRepository;
import de.cookyapp.persistence.repositories.IRecipeIngredientCrudRepository;
import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.exceptions.InvalidID;
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
        List<RecipeIngredientEntity> updatedEntities = new ArrayList<>( ingredients.size() );
        RecipeEntity recipe = this.recipeCrudRepository.findOne( recipeId );

        if ( recipe == null ) {
            throw new InvalidID( recipeId );
        } else {
            for ( Ingredient ingredient : ingredients ) {
                RecipeIngredientEntity entity = this.recipeIngredientCrudRepository.findByRecipeIdAndIngredientId( recipeId, ingredient.getId() );
                IngredientEntity ingredientEntity = this.ingredientCrudRepository.findOne( ingredient.getId() );

                if ( entity == null ) {
                    if ( ingredientEntity == null ) {
                        ingredientEntity = new IngredientEntity();
                        ingredientEntity.setName( ingredient.getName() );
                        ingredientEntity = this.ingredientCrudRepository.save( ingredientEntity );
                    }

                    entity = new RecipeIngredientEntity();
                    entity.setRecipe( recipe );
                } else {
                    if ( ingredient != null && !ingredientEntity.getName().equals( ingredient.getName() ) ) {
                        ingredientEntity = this.ingredientCrudRepository.findFirstByName( ingredient.getName() );

                        if ( ingredientEntity == null ) {
                            ingredientEntity = new IngredientEntity();
                            ingredientEntity.setName( ingredient.getName() );
                            ingredientEntity = this.ingredientCrudRepository.save( ingredientEntity );
                        }
                    }
                }

                entity.setAmount( ingredient.getAmount() );
                entity.setUnit( ingredient.getUnit() );
                entity.setIngredient( ingredientEntity );
                updatedEntities.add( entity );
            }
        }

        List<RecipeIngredientEntity> allIngredients = this.recipeIngredientCrudRepository.findByRecipeId( recipeId );
        List<RecipeIngredientEntity> entitiesToDelete = new ArrayList<>();
        for ( RecipeIngredientEntity recipeIngredient : allIngredients ) {
            if ( !updatedEntities.contains( recipeIngredient ) ) {
                entitiesToDelete.add( recipeIngredient );
            }
        }
        this.recipeIngredientCrudRepository.delete( entitiesToDelete );
        this.recipeIngredientCrudRepository.save( updatedEntities );
    }

    @Override
    public List<Ingredient> loadRecipeIngredients( int recipeId ) {
        List<RecipeIngredientEntity> recipeIngredientEntities = recipeIngredientCrudRepository.findByRecipeId( recipeId );
        List<Ingredient> ingredientList = new ArrayList<>();

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
}