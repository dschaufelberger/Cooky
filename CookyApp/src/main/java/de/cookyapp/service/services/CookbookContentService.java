package de.cookyapp.service.services;

import java.util.LinkedList;
import java.util.List;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.CookbookEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.repositories.app.ICookbookRepository;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;
import de.cookyapp.service.exceptions.DefaultCookbookContentNotManagable;
import de.cookyapp.service.exceptions.InvalidCookbookId;
import de.cookyapp.service.exceptions.InvalidRecipeId;
import de.cookyapp.service.exceptions.UserNotAuthorized;
import de.cookyapp.service.services.interfaces.ICookbookContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 02.05.2016.
 */
@Transactional
@Service
public class CookbookContentService implements ICookbookContentService {
    private IRecipeCrudRepository recipeRepository;
    private ICookbookRepository cookbookRepository;
    private IAuthenticationFacade authentication;

    @Autowired
    public CookbookContentService(
            IRecipeCrudRepository recipeRepository,
            ICookbookRepository cookbookRepository,
            IAuthenticationFacade authentication ) {
        this.recipeRepository = recipeRepository;
        this.cookbookRepository = cookbookRepository;
        this.authentication = authentication;
    }

    @Override
    public void addRecipeToCookbook( int cookbookId, int recipeId ) {
        addRecipe( cookbookId, recipeId, true );
    }

    @Override
    public void addRecipeToDefaultCookbook( int cookbookId, int recipeId ) {
        addRecipe( cookbookId, recipeId, false );
    }

    private void addRecipe( int cookbookId, int recipeId, boolean makeDefaultCheck ) {
        String currentUsername = this.authentication.getAuthentication().getName();
        CookbookEntity cookbook = this.cookbookRepository.findOne( cookbookId );

        if ( cookbook == null ) {
            throw new InvalidCookbookId( "Cookbook with given id does not exist.", cookbookId );
        }

        if ( makeDefaultCheck && cookbook.getIsDefault() ) {
            throw new DefaultCookbookContentNotManagable();
        }

        RecipeEntity recipe = this.recipeRepository.findOne( recipeId );
        if ( recipe == null ) {
            throw new InvalidRecipeId( "Recipe with the given id does not exist.", recipeId );
        }
        if ( !cookbook.getOwner().getUsername().equals( currentUsername ) ) {
            throw new UserNotAuthorized();
        }

        CookbookEntity existingMapping = this.cookbookRepository.findOneByIdAndRecipes_Id( cookbook.getId(), recipe.getId() );

        if ( existingMapping == null ) {
            cookbook.getRecipes().add( recipe );
            this.cookbookRepository.save( cookbook );
        }
    }

    @Override
    public void addRecipesToCookbook( int cookbookId, List<Integer> recipesIds ) {
        String currentUsername = this.authentication.getAuthentication().getName();
        CookbookEntity cookbook = this.cookbookRepository.findOne( cookbookId );

        if ( cookbook == null ) {
            throw new InvalidCookbookId( "Cookbook with given id does not exist.", cookbookId );
        }

        if ( cookbook.getIsDefault() ) {
            throw new DefaultCookbookContentNotManagable();
        }

        if ( !cookbook.getOwner().getUsername().equals( currentUsername ) ) {
            throw new UserNotAuthorized();
        }

        LinkedList<RecipeEntity> recipes = new LinkedList<>();
        for ( Integer id : recipesIds ) {
            RecipeEntity recipe = this.recipeRepository.findOne( id );
            if ( recipe == null ) {
                throw new InvalidRecipeId( "Recipe with the given id does not exist.", id );
            }

            CookbookEntity existingMapping = this.cookbookRepository.findOneByIdAndRecipes_Id( cookbook.getId(), recipe.getId() );

            if ( existingMapping == null ) {
                recipes.add( recipe );
            }
        }

        cookbook.getRecipes().addAll( recipes );
        this.cookbookRepository.save( cookbook );
    }

    @Override
    public void removeRecipeFromCookbook( int cookbookId, int recipeId ) {
        String currentUsername = this.authentication.getAuthentication().getName();
        CookbookEntity cookbook = this.cookbookRepository.findOne( cookbookId );

        if ( cookbook == null ) {
            throw new InvalidCookbookId( "Cookbook with given id does not exist.", cookbookId );
        }

        if ( cookbook.getIsDefault() ) {
            throw new DefaultCookbookContentNotManagable();
        }

        RecipeEntity recipe = this.recipeRepository.findOne( recipeId );
        if ( recipe != null ) {
            if ( !cookbook.getOwner().getUsername().equals( currentUsername ) ) {
                throw new UserNotAuthorized();
            }

            if ( cookbook.getRecipes().remove( recipe ) ) {
                this.cookbookRepository.save( cookbook );
            }
        } else {
            throw new InvalidRecipeId( recipeId );
        }
    }

    @Override
    public void removeAllRecipesFromCookbook( int cookbookId ) {
        String currentUsername = this.authentication.getAuthentication().getName();
        CookbookEntity cookbook = this.cookbookRepository.findOne( cookbookId );

        if ( cookbook == null ) {
            throw new InvalidCookbookId( "Cookbook with given id does not exist.", cookbookId );
        }

        if ( cookbook.getIsDefault() ) {
            throw new DefaultCookbookContentNotManagable();
        }

        if ( !cookbook.getOwner().getUsername().equals( currentUsername ) ) {
            throw new UserNotAuthorized();
        }

        cookbook.setRecipes( new LinkedList<>() );
        this.cookbookRepository.save( cookbook );
    }

    @Override
    public void moveRecipeBetweenCookbooks( int recipeId, int currentCookbookId, int newCookbookId ) {
        if ( currentCookbookId != newCookbookId ) {
            removeRecipeFromCookbook( currentCookbookId, recipeId );
            addRecipeToCookbook( newCookbookId, recipeId );
        }
    }
}
