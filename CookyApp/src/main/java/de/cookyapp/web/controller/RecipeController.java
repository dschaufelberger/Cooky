package de.cookyapp.web.controller;

import de.cookyapp.persistence.dao.IngredientDao;
import de.cookyapp.persistence.dao.RecipeDao;
import de.cookyapp.persistence.dao.UserDao;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import de.cookyapp.viewmodel.Ingredient;
import de.cookyapp.viewmodel.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Jasper on 27.11.2015.
 */

@Controller
@RequestMapping( "/recipes" )
public class RecipeController {
    private UserDao userDao;
    private RecipeDao recipeDao;
    private IngredientDao ingredientDao;

    @Autowired
    public RecipeController( UserDao userDao, RecipeDao recipeDao, IngredientDao ingredientDao ) {
        this.userDao = userDao;
        this.recipeDao = recipeDao;
        this.ingredientDao = ingredientDao;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView handleRecipes() {
        ModelAndView model = new ModelAndView( "RecipeOverviewTile" );
        model.addObject( "recipesList", this.recipeDao.getAllRecipes() );
        return model;
    }

    @RequestMapping( "/removeRecipe" )
    public String handleRemoveRecipe( @RequestParam( "id" ) int id ) {
        //TODO Prüfen ob der eingeloggte Benutzer auch der ist, dem das gehört!!
        this.recipeDao.deleteRecipeById( id );
        return "redirect:/recipes";
    }

    @RequestMapping( "/editRecipe" )
    public String handleEditRecipeFinish( @ModelAttribute( "recipe" ) @Valid Recipe recipe, BindingResult bindingResult ) {
        String view;
        if ( bindingResult.hasErrors() ) {
            view = "RecipeEditTile";
        } else {
            RecipeEntity recipeEntity = this.recipeDao.loadWithLazyRelations( recipe.getId() );
            Iterator<RecipeIngredientEntity> iterator = recipeEntity.getIngredients().iterator();
            IngredientEntity ingredientEntity;
            RecipeIngredientEntity entity;
            for ( Ingredient ingredient : recipe.getIngredients() ) {
                entity = iterator.next();
                ingredientEntity = this.ingredientDao.getIngredientByName( ingredient.getName() );

                if ( ingredientEntity == null ) {
                    ingredientEntity = new IngredientEntity();
                    ingredientEntity.setName( ingredient.getName() );

                    this.ingredientDao.save( ingredientEntity );

                    entity.setIngredient( this.ingredientDao.getIngredientByName( ingredient.getName() ) );
                } else {
                    entity.setIngredient( ingredientEntity );
                }

                entity.setUnit( ingredient.getUnit() );
                entity.setAmount( ingredient.getAmount() );
                //entity.getIngredient().setName(ingredient.getName());
                //}
            }

            recipeEntity.setName( recipe.getName() );
            recipeEntity.setShortDescription( recipe.getShortDescription() );
            recipeEntity.setCalories( recipe.getCalories() );
            recipeEntity.setCookingTime( recipe.getCookingTime() );
            recipeEntity.setWorkingTime( recipe.getWorkingTime() );
            recipeEntity.setDifficulty( recipe.getDifficulty() );
            recipeEntity.setServing( recipe.getServing() );
            recipeEntity.setPreparation( recipe.getPreparation() );
            recipeEntity.setRestTime( recipe.getRestTime() );
            recipeEntity.setImageFileName( "http://placehold.it/320x200" );
            this.recipeDao.editRecipe( recipeEntity );
            view = "redirect:/recipes";
        }

        return view;
    }

    @RequestMapping( "/goToEditRecipe" )
    public ModelAndView handleEditRecipe( @RequestParam( "id" ) int id ) {
        Recipe recipe = new Recipe( this.recipeDao.getRecipeById( id ) );
        ModelAndView model = new ModelAndView( "RecipeEditTile", "recipe", recipe );
        return model;
    }

    @RequestMapping( "/goToAddRecipe" )
    public ModelAndView handleGoToRecipe() {
        ModelAndView model = new ModelAndView( "RecipeCreationTile", "recipe", new Recipe() );
        return model;
    }

    @RequestMapping( value = "/create" )
    public String handleAddRecipe( @ModelAttribute( "recipe" ) @Valid Recipe recipe, BindingResult bindingResult ) {
        String view;
        if ( bindingResult.hasErrors() ) {
            view = "RecipeCreationTile";
        } else {

            RecipeEntity recipeEntity = new RecipeEntity( recipe );

            //TODO Das muss später unbedingt noch geändert werden!!!
            recipeEntity.setAuthor( this.userDao.load( 7 ) );

            ArrayList<RecipeIngredientEntity> ingredientList = new ArrayList<RecipeIngredientEntity>();
            for ( Ingredient ingredient : recipe.getIngredients() ) {
                IngredientEntity current = new IngredientEntity();
                if ( this.ingredientDao.getIngredientByName( ingredient.getName() ) == null ) {
                    current.setName( ingredient.getName() );
                } else {
                    current = this.ingredientDao.getIngredientByName( ingredient.getName() );
                }

                RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity();
                recipeIngredientEntity.setIngredient( current );
                recipeIngredientEntity.setAmount( ingredient.getAmount() );
                recipeIngredientEntity.setUnit( ingredient.getUnit() );
                recipeIngredientEntity.setRecipe( recipeEntity );

                ingredientList.add( recipeIngredientEntity );
            }
            recipeEntity.setIngredients( ingredientList );
            recipeEntity.setImageFileName( "http://placehold.it/320x200" );
            this.recipeDao.addRecipe( recipeEntity );
            view = "redirect:/recipes";
        }
        return view;
    }
}
