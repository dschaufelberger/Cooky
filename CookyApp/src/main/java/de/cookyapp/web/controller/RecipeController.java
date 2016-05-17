package de.cookyapp.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.authentication.IUserAuthorization;
import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.exceptions.InvalidContentFileFormat;
import de.cookyapp.service.services.interfaces.IImageUploadService;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import de.cookyapp.service.services.interfaces.IRecipeRatingService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.Recipe;
import de.cookyapp.web.viewmodel.Search;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Jasper on 27.11.2015.
 */

@Controller
@RequestMapping( "/recipes" )
public class RecipeController {
    private Logger logger = Logger.getLogger( RecipeController.class );

    private IUserCrudService userCrudService;
    private IRecipeCrudService recipeCrudService;
    private IIngredientCrudService ingredientCrudService;
    private IImageUploadService imageService;
    private IRecipeRatingService ratingService;
    private IAuthenticationFacade authentication;

    @Autowired
    public RecipeController( IUserCrudService userCrudService, IRecipeCrudService recipeCrudService, IIngredientCrudService ingredientCrudService,
                             IAuthenticationFacade authenticationFacade, IUserAuthorization userAuthorization, IImageUploadService imageService,
                             IRecipeRatingService ratingService ) {
        this.userCrudService = userCrudService;
        this.recipeCrudService = recipeCrudService;
        this.ingredientCrudService = ingredientCrudService;
        this.imageService = imageService;
        this.authentication = authenticationFacade;
        this.userAuthorization = userAuthorization;
        this.ratingService = ratingService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView handleRecipes() {
        ModelAndView model = new ModelAndView( "RecipeOverviewTile" );
        model.addObject( "recipesList", this.recipeCrudService.getAllRecipes() );
        return model;
    }

    @RequestMapping( "/removeRecipe" )
    public String handleRemoveRecipe( @RequestParam( "id" ) int id ) {
        this.recipeCrudService.deleteRecipe( id );
        return "redirect:/recipes";
    }

    @RequestMapping ( "/search" )
    public ModelAndView search ( @ModelAttribute ("search") @Valid Search search) {
        ModelAndView modelAndView = new ModelAndView( "RecipeOverviewTile" );
        modelAndView.addObject( "recipesList", recipeCrudService.searchRecipesContaining( search.getSearchQuery() ));
        return modelAndView;
    }

    @RequestMapping( "/editRecipe" )
    public String handleEditRecipeFinish( @ModelAttribute( "recipe" ) @Valid Recipe recipe, @RequestParam( "recipeImage" ) MultipartFile image, BindingResult bindingResult ) {
        String view;
        boolean isAuthorized = this.authentication.getAuthentication().getName().equals( recipeCrudService.getRecipe( recipe.getId() ).getAuthor().getUsername() );
        if ( bindingResult.hasErrors() ) {
            view = "RecipeEditTile";
        } else {
            if ( isAuthorized ) {
                de.cookyapp.service.dto.Recipe recipeDTO = this.recipeCrudService.getRecipe( recipe.getId() );
                ArrayList<de.cookyapp.web.viewmodel.Ingredient> newIngredients = new ArrayList<>( recipe.getIngredients() );
                List<Ingredient> ingredients = new ArrayList<>();
                for ( de.cookyapp.web.viewmodel.Ingredient current : newIngredients ) {
                    if ( current != null ) {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setAmount( current.getAmount() );
                        ingredient.setName( current.getName() );
                        ingredient.setUnit( current.getUnit() );
                        ingredient.setId( current.getId() );
                        ingredients.add( ingredient );
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
                recipeCrudService.updateRecipe( recipeDTO );

                uploadImage( image, recipeDTO.getId() );

                ingredientCrudService.saveRecipeIngredient( recipeDTO.getId(), ingredients );
            }
            view = "redirect:/recipes";
        }

        return view;
    }

    @RequestMapping( "/view/{id}" )
    public ModelAndView showDetail( @PathVariable int id ) {
        Recipe recipe = new Recipe( this.recipeCrudService.getRecipe( id ), ingredientCrudService.loadRecipeIngredients( id ) );
        Collection<de.cookyapp.web.viewmodel.Ingredient> ingredientCollection = new ArrayList<>();
        List<Ingredient> ingredients = ingredientCrudService.loadRecipeIngredients( id );
        for ( Ingredient current : ingredients ) {
            de.cookyapp.web.viewmodel.Ingredient ingredientViewmodel = new de.cookyapp.web.viewmodel.Ingredient();
            ingredientViewmodel.setAmount( current.getAmount() );
            ingredientViewmodel.setName( current.getName() );
            ingredientViewmodel.setUnit( current.getUnit() );
            ingredientViewmodel.setId( current.getId() );
            ingredientCollection.add( ingredientViewmodel );
        }
        recipe.setIngredients( ingredientCollection );
        ModelAndView model = new ModelAndView( "RecipeEditTile", "recipe", recipe );
        return model;
    }

    @RequestMapping( "/goToAddRecipe" )
    public ModelAndView handleGoToRecipe() {
        ModelAndView model = new ModelAndView( "RecipeCreationTile", "recipe", new Recipe() );
        return model;
    }

    @RequestMapping( value = "/addRecipe" )
    public String handleAddRecipe( @ModelAttribute( "recipe" ) @Valid Recipe recipe, BindingResult bindingResult, @RequestParam( "recipeImage" ) MultipartFile image ) {
        String view;
        if ( bindingResult.hasErrors() ) {
            view = "RecipeCreationTile";
        } else {
            de.cookyapp.service.dto.Recipe newRecipe = new de.cookyapp.service.dto.Recipe();
            newRecipe.setName( recipe.getName() );
            newRecipe.setWorkingTime( recipe.getWorkingTime() );
            newRecipe.setRestTime( recipe.getRestTime() );
            newRecipe.setPreparation( recipe.getPreparation() );
            newRecipe.setCalories( recipe.getCalories() );
            newRecipe.setCookingTime( recipe.getCookingTime() );
            newRecipe.setDifficulty( recipe.getDifficulty() );
            newRecipe.setServing( recipe.getServing() );
            newRecipe.setShortDescription( recipe.getShortDescription() );
            newRecipe.setCreationDate( LocalDateTime.now() );

            List<de.cookyapp.web.viewmodel.Ingredient> ingredientList = new ArrayList<>( recipe.getIngredients() );
            List<Ingredient> ingredients = new ArrayList<>();
            for ( de.cookyapp.web.viewmodel.Ingredient current : ingredientList ) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName( current.getName() );
                ingredient.setAmount( current.getAmount() );
                ingredient.setUnit( current.getUnit() );
                ingredients.add( ingredient );
            }

            ingredientCrudService.save( ingredients );
            de.cookyapp.service.dto.Recipe current = recipeCrudService.createRecipe( newRecipe );

            uploadImage( image, current.getId() );

            ingredientCrudService.saveRecipeIngredient( current.getId(), ingredients );
            view = "redirect:/recipes";
        }
        return view;
    }

    @RequestMapping( "/rateRecipe" )
    public String rateRecipe( @RequestParam( "id" ) int id, @RequestParam( "rating" ) byte rating ) {
        ratingService.rateRecipe( id, rating );
        String view = "redirect:/recipes/view/" + id;
        return view;
    }

    private void validateImage( MultipartFile image ) {
        if ( !image.getContentType().equals( "image/jpeg" ) && !image.getContentType().equals( "image/jpg" ) ) {
            throw new InvalidContentFileFormat( image.getName(), image.getContentType(), "Only JPG images are accepted" );
        }
    }

    private void uploadImage( MultipartFile image, int recipeId ) {
        if ( image != null ) {
            validateImage( image );
            InputStream inputStream = null;
            BufferedImage bufferedImage = null;
            try {
                try {
                    inputStream = image.getInputStream();
                    bufferedImage = ImageIO.read( inputStream );
                } catch ( Exception i ) {
                    logger.error( "Upload Image ist fehlgeschlagen" );
                    throw i;
                } finally {
                    if ( inputStream != null ) {
                        inputStream.close();
                    }
                }
                imageService.saveImage( recipeId, bufferedImage );
            } catch ( Exception ex ) {
                logger.error( ex.getMessage(), ex );
            }
        }
    }
}
