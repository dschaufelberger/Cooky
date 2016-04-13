package de.cookyapp.service.services;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.authentication.IUserAuthorization;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import de.cookyapp.persistence.repositories.IRecipeCrudRepository;
import de.cookyapp.persistence.repositories.IRecipeIngredientCrudRepository;
import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.services.interfaces.IRecipeIngredientCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 12.04.2016.
 */
@Transactional
@Service
public class RecipeIngredientCrudService implements IRecipeIngredientCrudService{
    private IRecipeIngredientCrudRepository recipeIngredientCrudRepository;
    private IRecipeCrudRepository recipeCrudRepository;
    private IAuthenticationFacade authentication;
    private IUserAuthorization userAuthorization;

    @Autowired
    public RecipeIngredientCrudService (IRecipeIngredientCrudRepository recipeIngredientCrudRepository, IAuthenticationFacade authentication, IUserAuthorization userAuthorization, IRecipeCrudRepository recipeCrudRepository) {
        this.recipeIngredientCrudRepository = recipeIngredientCrudRepository;
        this.authentication = authentication;
        this.userAuthorization = userAuthorization;
        this.recipeCrudRepository = recipeCrudRepository;
    }

    @Override
    public String loadAmount(int id) {
        RecipeIngredientEntity recipeIngredientEntity = recipeIngredientCrudRepository.findOne(id);
        String amount = "";
        if (recipeIngredientEntity != null) {
            amount = recipeIngredientEntity.getAmount();
        }
        return amount;
    }

    @Override
    public String loadUnit(int id) {
        RecipeIngredientEntity recipeIngredientEntity = recipeIngredientCrudRepository.findOne(id);
        String unit = "";
        if (recipeIngredientEntity != null) {
            unit = recipeIngredientEntity.getUnit();
        }
        return unit;
    }

    @Override
    public List<Ingredient> getRecipeIngredients(int recipeId) {
        List<RecipeIngredientEntity> recipeIngredientEntities = recipeIngredientCrudRepository.findByRecipeId(recipeId);
        List<Ingredient> ingredientList = new ArrayList<>();
        for (RecipeIngredientEntity current : recipeIngredientEntities) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(current.getId());
            ingredient.setUnit(current.getUnit());
            ingredient.setAmount(current.getAmount());
        }
        return ingredientList;
    }

    @Override
    public void saveRecipeIngredient(int recipeId, Ingredient ingredient) {
        RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity();
        IngredientEntity ingredientEntity = new IngredientEntity();

        recipeIngredientEntity.setAmount(ingredient.getAmount());
        recipeIngredientEntity.setUnit(ingredient.getAmount());
        recipeIngredientEntity.setRecipe(recipeCrudRepository.findOne(recipeId));

        ingredientEntity.setId(ingredient.getId());
        ingredientEntity.setName(ingredient.getName());

        recipeIngredientEntity.setIngredient(ingredientEntity);

        recipeIngredientCrudRepository.save(recipeIngredientEntity);
    }
}
