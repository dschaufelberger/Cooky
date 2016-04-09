package de.cookyapp.service.services;

import java.util.List;

import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
@Transactional
@Service
public class RecipeCrudService implements IRecipeCrudService {
    @Override
    public void deleteRecipe( int recipeID ) {
        throw new NotImplementedException();
    }

    @Override
    public void createRecipe( Recipe recipe ) {
        throw new NotImplementedException();
    }

    @Override
    public void updateRecipe( Recipe recipe ) {
        throw new NotImplementedException();
    }

    @Override
    public Recipe getRecipe( int recipeID ) {
        throw new NotImplementedException();
    }

    @Override
    public List<Recipe> getAllRecipes() {
        throw new NotImplementedException();
    }

    @Override
    public List<Recipe> getAllRecipesByName( String recipeName ) {
        throw new NotImplementedException();
    }

    @Override
    public List<Recipe> searchRecipesContaining( String searchTerm ) {
        throw new NotImplementedException();
    }
}
