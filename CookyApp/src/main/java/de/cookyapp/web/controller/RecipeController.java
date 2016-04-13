package de.cookyapp.web.controller;

import javax.validation.Valid;

import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import de.cookyapp.service.services.interfaces.IRecipeIngredientCrudService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by Jasper on 27.11.2015.
 */

@Controller
@RequestMapping( "/recipes" )
public class RecipeController {
    private IUserCrudService userCrudService;
    private IRecipeCrudService recipeCrudService;
    private IIngredientCrudService ingredientCrudService;
    private IRecipeIngredientCrudService recipeIngredientCrudService;

    @Autowired
    public RecipeController( IUserCrudService userCrudService, IRecipeCrudService recipeCrudService, IIngredientCrudService ingredientCrudService, IRecipeIngredientCrudService recipeIngredientCrudService ) {
        this.userCrudService = userCrudService;
        this.recipeCrudService = recipeCrudService;
        this.ingredientCrudService = ingredientCrudService;
        this.recipeIngredientCrudService = recipeIngredientCrudService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView handleRecipes() {
        ModelAndView model = new ModelAndView( "RecipeOverviewTile" );
        model.addObject( "recipesList", this.recipeCrudService.getAllRecipes() );
        return model;
    }

    @RequestMapping( "/removeRecipe" )
    public String handleRemoveRecipe( @RequestParam( "id" ) int id ) {
        //TODO Prüfen ob der eingeloggte Benutzer auch der ist, dem das gehört!!
        this.recipeCrudService.deleteRecipe( id );
        return "redirect:/recipes";
    }

    @RequestMapping( "/editRecipe" )
    public String handleEditRecipeFinish( @ModelAttribute( "recipe" ) @Valid Recipe recipe, BindingResult bindingResult ) {
        String view;
        if ( bindingResult.hasErrors() ) {
            view = "RecipeEditTile";
        } else {
            de.cookyapp.service.dto.Recipe recipeDTO = this.recipeCrudService.getRecipe( recipe.getId() );
            ArrayList<de.cookyapp.web.viewmodel.Ingredient> newIngredients = new ArrayList<>(recipe.getIngredients());

            for (de.cookyapp.web.viewmodel.Ingredient current : newIngredients) {
                if (current != null) {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setAmount(current.getAmount());
                    ingredient.setName(current.getName());
                    ingredient.setUnit(current.getUnit());
                    ingredient.setId(current.getId());
                    recipeIngredientCrudService.saveRecipeIngredient(recipeDTO.getId(), ingredient); //Macht save auch ein update?
                    ingredientCrudService.save(ingredient);
                }

            }

            recipeDTO.setName( recipe.getName() );
            recipeDTO.setShortDescription( recipe.getShortDescription() );
            recipeDTO.setCalories( recipe.getCalories() );
            recipeDTO.setCookingTime( recipe.getCookingTime() );
            recipeDTO.setWorkingTime( recipe.getWorkingTime() );
            recipeDTO.setDifficulty( recipe.getDifficulty() );
            recipeDTO.setServing( recipe.getServing() );
            recipeDTO.setPreparation( recipe.getPreparation() );
            recipeDTO.setRestTime( recipe.getRestTime() );
            recipeDTO.setImageFileName( "http://placehold.it/320x200" );
            this.recipeCrudService.updateRecipe( recipeDTO);

            /*List<Ingredient> ingredientList = recipeIngredientCrudService.getRecipeIngredients(recipeDTO.getId());
            List<Ingredient> complete = new ArrayList<>();

            for (Ingredient current : ingredientList) {
                Ingredient ingredient = new Ingredient();
                ingredient.setAmount(current.getAmount());
                ingredient.setUnit(current.getUnit());
                ingredient.setName(ingredientCrudService.getIngredientName(current.getId()));

                System.out.println(ingredient.getName() + " " + ingredient.getAmount() + " " + ingredient.getUnit() );

                complete.add(ingredient);
            } */


            /* TODO [dodo] der de.cookyapp.service.dto.Recipe Klasse müssen die Bestandteile eines Rezeptes (Ingredients & Co.) hinzugefügt werden.
             * Von dieser Klasse wird dann ein Objekt erzeugt, dort die Ingredients hinzugefügt und das Rezept dann über
             * this.recipeCrudService.updateRecipe(recipeDTO); abgespeichert.
             * Die Logik des Abspeicherns der einzelnen Ingredients findet dann in dieser Service-Method statt! Dazu erhält
             * der RecipeCrudService noch ein IIngredientService oder IIngredientCrudRepository Objekt injiziert (was da im Endeffekt mehr Sinn macht).
             */

            //Iterator<RecipeIngredientEntity> iterator = recipeEntity.getIngredients().iterator();
            //IngredientEntity ingredientEntity;
            //RecipeIngredientEntity entity;
            //for ( Ingredient ingredient : recipe.getIngredients() ) {
            //    entity = iterator.next();
            //    ingredientEntity = this.ingredientDao.getIngredientByName( ingredient.getName() );
            //
            //    if ( ingredientEntity == null ) {
            //        ingredientEntity = new IngredientEntity();
            //        ingredientEntity.setName( ingredient.getName() );
            //
            //        this.ingredientDao.save( ingredientEntity );
            //
            //        entity.setIngredient( this.ingredientDao.getIngredientByName( ingredient.getName() ) );
            //    } else {
            //        entity.setIngredient( ingredientEntity );
            //    }
            //
            //    entity.setUnit( ingredient.getUnit() );
            //    entity.setAmount( ingredient.getAmount() );
            //}
            //
            //recipeEntity.setName( recipe.getName() );
            //recipeEntity.setShortDescription( recipe.getShortDescription() );
            //recipeEntity.setCalories( recipe.getCalories() );
            //recipeEntity.setCookingTime( recipe.getCookingTime() );
            //recipeEntity.setWorkingTime( recipe.getWorkingTime() );
            //recipeEntity.setDifficulty( recipe.getDifficulty() );
            //recipeEntity.setServing( recipe.getServing() );
            //recipeEntity.setPreparation( recipe.getPreparation() );
            //recipeEntity.setRestTime( recipe.getRestTime() );
            //recipeEntity.setImageFileName( "http://placehold.it/320x200" );
            //this.recipeCrudService.editRecipe( recipeEntity );
            view = "redirect:/recipes";
        }

        return view;
    }

    @RequestMapping( "/goToEditRecipe" )
    public ModelAndView handleEditRecipe( @RequestParam( "id" ) int id ) {
        Recipe recipe = new Recipe( this.recipeCrudService.getRecipe( id ) );
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

            //  TODO [dodo] Hier bitte die entsprechende Implementierung korrigieren.
            //  Hier kann ja testweise erstmal von dem Branch mit den Änderungen gemerged werden.

            //RecipeEntity recipeEntity = new RecipeEntity( recipe );
            //
            //recipeEntity.setAuthor( this.userCrudService.load( 7 ) );
            //
            //ArrayList<RecipeIngredientEntity> ingredientList = new ArrayList<RecipeIngredientEntity>();
            //for ( Ingredient ingredient : recipe.getIngredients() ) {
            //    IngredientEntity current = new IngredientEntity();
            //    if ( this.ingredientDao.getIngredientByName( ingredient.getName() ) == null ) {
            //        current.setName( ingredient.getName() );
            //    } else {
            //        current = this.ingredientDao.getIngredientByName( ingredient.getName() );
            //    }
            //
            //    RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity();
            //    recipeIngredientEntity.setIngredient( current );
            //    recipeIngredientEntity.setAmount( ingredient.getAmount() );
            //    recipeIngredientEntity.setUnit( ingredient.getUnit() );
            //    recipeIngredientEntity.setRecipe( recipeEntity );
            //
            //    ingredientList.add( recipeIngredientEntity );
            //}
            //recipeEntity.setIngredients( ingredientList );
            //recipeEntity.setImageFileName( "http://placehold.it/320x200" );
            //this.recipeCrudService.addRecipe( recipeEntity );
            view = "redirect:/recipes";
        }
        return view;
    }
}
