package de.cookyapp.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.service.exceptions.ImageUploadFailed;
import de.cookyapp.service.exceptions.InvalidRecipeId;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import de.cookyapp.service.services.interfaces.IRecipeRatingService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.recipes.Ingredient;
import de.cookyapp.web.viewmodel.recipes.Recipe;
import de.cookyapp.web.viewmodel.recipes.Search;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    private IRecipeRatingService ratingService;
    private IAuthenticationFacade authentication;
    private ICookbookManagementService cookbookManagementService;

    @Autowired
    public RecipeController( IUserCrudService userCrudService, IRecipeCrudService recipeCrudService, IIngredientCrudService ingredientCrudService,
                             IAuthenticationFacade authenticationFacade, IRecipeRatingService ratingService,
                             ICookbookManagementService cookbookManagementService ) {
        this.userCrudService = userCrudService;
        this.recipeCrudService = recipeCrudService;
        this.ingredientCrudService = ingredientCrudService;
        this.authentication = authenticationFacade;
        this.ratingService = ratingService;
        this.cookbookManagementService = cookbookManagementService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView handleRecipes() {
        ModelAndView model = new ModelAndView( "RecipeOverviewTile" );
        model.addObject( "recipesList", this.recipeCrudService.getAllRecipes().stream().map( recipe -> new Recipe( recipe ) ).collect( Collectors.toList() ) );
        return model;
    }

    @RequestMapping( value = "/remove", method = RequestMethod.POST )
    public String handleRemoveRecipe( @RequestParam( "id" ) int id ) {
        this.recipeCrudService.deleteRecipe( id );
        return "redirect:/recipes";
    }

    @RequestMapping( value = "/search", method = RequestMethod.GET )
    public ModelAndView search( @ModelAttribute( "search" ) @Valid Search search ) {
        ModelAndView modelAndView = new ModelAndView( "RecipeOverviewTile" );
        modelAndView.addObject( "recipesList", recipeCrudService.searchRecipesContaining( search.getSearchQuery() ) );
        return modelAndView;
    }

    @RequestMapping( value = "/save", method = RequestMethod.POST )
    public String handleEditRecipeFinish( @ModelAttribute( "recipe" ) @Valid Recipe recipe, BindingResult bindingResult, @RequestParam( "recipeImage" ) MultipartFile image ) {
        String view;
        if ( bindingResult.hasErrors() ) {
            view = "RecipeEditTile";
        } else {
            ArrayList<String> contentTypes = new ArrayList<>( 2 );
            contentTypes.add( "image/jpeg" );
            contentTypes.add( "image/jpg" );

            if ( !validateImage( image, contentTypes ) ) {
                bindingResult.addError( new FieldError( "recipeImage", "imageLink", "Only JPEG images are allowed." ) );

                return "RecipeCreationTile";
            }

            de.cookyapp.service.dto.Recipe recipeDTO = this.recipeCrudService.getRecipe( recipe.getId() );
            if ( recipeDTO == null ) {
                throw new InvalidRecipeId( recipe.getId() );
            } else {
                boolean isAuthorized = this.authentication.getAuthentication().getName().equals( recipeDTO.getAuthor().getUsername() );
                if ( isAuthorized ) {
                    recipeDTO.setName( recipe.getName() );
                    recipeDTO.setShortDescription( recipe.getShortDescription() );
                    recipeDTO.setPreparation( recipe.getPreparation() );
                    recipeDTO.setWorkingTime( recipe.getWorkingTime() );
                    recipeDTO.setCookingTime( recipe.getCookingTime() );
                    recipeDTO.setRestTime( recipe.getRestTime() );
                    recipeDTO.setCalories( recipe.getCalories() );
                    recipeDTO.setDifficulty( recipe.getDifficulty() );
                    recipeDTO.setServing( recipe.getServing() );
                    recipeDTO.setImageData( getImageBytes( image ) );

                    ArrayList<Ingredient> ingredientViewmodels = new ArrayList<>( recipe.getIngredients() );
                    ArrayList<de.cookyapp.service.dto.Ingredient> ingredients = new ArrayList<>( ingredientViewmodels.size() );
                    for ( Ingredient current : ingredientViewmodels ) {
                        if ( current != null ) {
                            de.cookyapp.service.dto.Ingredient ingredient = new de.cookyapp.service.dto.Ingredient();
                            ingredient.setId( current.getId() );
                            ingredient.setName( current.getName() );
                            ingredient.setAmount( current.getAmount() );
                            ingredient.setUnit( current.getUnit() );
                            ingredients.add( ingredient );
                        }
                    }
                    recipeDTO.setIngredients( ingredients );

                    recipeCrudService.updateRecipe( recipeDTO );
                }
                view = "redirect:/recipes";
            }
        }

        return view;
    }

    @RequestMapping( value = "/view/{id}", method = RequestMethod.GET )
    public ModelAndView showDetail( @PathVariable int id ) {
        ModelAndView modelAndView = new ModelAndView( "RecipeDetailTile" );

        Recipe recipe = new Recipe( this.recipeCrudService.getRecipe( id ), ingredientCrudService.loadRecipeIngredients( id ) );
        recipe.setShortDescription( htmlEscapeNewLines( recipe.getShortDescription() ) );
        recipe.setPreparation( htmlEscapeNewLines( recipe.getPreparation() ) );

        // TODO needed in edit view
        //User user = this.userCrudService.getCurrentUser();
        //
        //if ( user != null ) {
        //    List<Cookbook> cookbooks = this.cookbookManagementService.getCookbooksForUser( user.getId() )
        //            .stream()
        //            .map( cookbook -> new Cookbook( cookbook ) )
        //            .collect( Collectors.toList() );
        //
        //    CookbookOverview overview = new CookbookOverview( cookbooks );
        //    RecipeCookbook cookbook = new RecipeCookbook();
        //    cookbook.setRecipeId( id );
        //
        //    modelAndView.addObject( "cookbook", cookbook );
        //    modelAndView.addObject( "cookbookOverview", overview );
        //}
        modelAndView.addObject( "recipe", recipe );

        return modelAndView;
    }

    @RequestMapping( value = "/add", method = RequestMethod.GET )
    public ModelAndView handleGoToRecipe() {
        ModelAndView model = new ModelAndView( "RecipeCreationTile", "recipe", new Recipe() );
        return model;
    }

    @RequestMapping( value = "/create", method = RequestMethod.POST )
    public String handleAddRecipe( @ModelAttribute( "recipe" ) @Valid Recipe recipe, BindingResult bindingResult, @RequestParam( "recipeImage" ) MultipartFile image ) {
        String view;
        if ( bindingResult.hasErrors() ) {
            view = "RecipeCreationTile";
        } else {
            ArrayList<String> contentTypes = new ArrayList<>( 2 );
            contentTypes.add( "image/jpeg" );
            contentTypes.add( "image/jpg" );

            if ( !validateImage( image, contentTypes ) ) {
                bindingResult.addError( new FieldError( "recipeImage", "imageLink", "Only JPEG images are allowed." ) );

                return "RecipeCreationTile";
            }

            de.cookyapp.service.dto.Recipe newRecipe = new de.cookyapp.service.dto.Recipe();
            newRecipe.setName( recipe.getName() );
            newRecipe.setShortDescription( recipe.getShortDescription() );
            newRecipe.setPreparation( recipe.getPreparation() );
            newRecipe.setWorkingTime( recipe.getWorkingTime() );
            newRecipe.setCookingTime( recipe.getCookingTime() );
            newRecipe.setRestTime( recipe.getRestTime() );
            newRecipe.setCalories( recipe.getCalories() );
            newRecipe.setDifficulty( recipe.getDifficulty() );
            newRecipe.setServing( recipe.getServing() );
            newRecipe.setCreationDate( LocalDateTime.now() );
            newRecipe.setImageData( getImageBytes( image ) );

            ArrayList<Ingredient> ingredientViewmodels = new ArrayList<>( recipe.getIngredients() );
            ArrayList<de.cookyapp.service.dto.Ingredient> ingredients = new ArrayList<>( ingredientViewmodels.size() );
            for ( Ingredient current : ingredientViewmodels ) {
                if ( current != null ) {
                    de.cookyapp.service.dto.Ingredient ingredient = new de.cookyapp.service.dto.Ingredient();
                    ingredient.setName( current.getName() );
                    ingredient.setAmount( current.getAmount() );
                    ingredient.setUnit( current.getUnit() );
                    ingredients.add( ingredient );
                }
            }
            newRecipe.setIngredients( ingredients );

            de.cookyapp.service.dto.Recipe current = recipeCrudService.createRecipe( newRecipe );

            view = "redirect:/recipes/view/" + current.getId();
        }
        return view;
    }

    @RequestMapping( value = "/rate", method = RequestMethod.POST )
    public String rateRecipe( @RequestParam( "id" ) int id, @RequestParam( "rating" ) byte rating ) {
        ratingService.rateRecipe( id, rating );
        String view = "redirect:/recipes/view/" + id;
        return view;
    }

    private String htmlEscapeNewLines( String text ) {
        return text.replaceAll( "\\r?\\n", "<br>" );
    }

    private byte[] getImageBytes( MultipartFile image ) {
        if ( image.isEmpty() ) {
            return null;
        }

        try {
            return image.getBytes();
        } catch ( IOException e ) {
            logger.error( "Error on uploading image. " +
                            "Image name: " + image.getName() +
                            ", File size: " + image.getSize() +
                            ", Content type: " + image.getContentType(),
                    e );
            throw new ImageUploadFailed( image.getName(), image.getContentType(), e );
        }
    }

    private boolean validateImage( MultipartFile image, List<String> validImageContentTypes ) {
        boolean isValid = false;

        if ( image.isEmpty() ) {
            return true;
        }

        for ( String contentType : validImageContentTypes ) {
            isValid |= image.getContentType().equals( contentType );
        }

        return isValid;
    }
}
