package de.cookyapp.web.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.ImageUploadFailed;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import de.cookyapp.service.services.interfaces.IRecipeRatingService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.Recipe;
import de.cookyapp.web.viewmodel.RecipeCookbook;
import de.cookyapp.web.viewmodel.Search;
import de.cookyapp.web.viewmodel.cookbook.Cookbook;
import de.cookyapp.web.viewmodel.cookbook.CookbookOverview;
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
        model.addObject( "recipesList", this.recipeCrudService.getAllRecipes() );
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

    @RequestMapping( value = "/edit", method = RequestMethod.POST )
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

            boolean isAuthorized = this.authentication.getAuthentication().getName().equals( recipeCrudService.getRecipe( recipe.getId() ).getAuthor().getUsername() );
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

                try {
                    recipeDTO.setImageData( image.getBytes() );
                } catch ( IOException e ) {
                    logger.error( "Error on uploading image. " +
                                    "Image name: " + image.getName() +
                                    ", Content type: " + image.getContentType() +
                                    ", Recipe name: " + recipe.getName(),
                            e );
                    throw new ImageUploadFailed( image.getName(), image.getContentType(), e );
                }

                recipeCrudService.updateRecipe( recipeDTO );
                ingredientCrudService.saveRecipeIngredient( recipeDTO.getId(), ingredients );
            }
            view = "redirect:/recipes";
        }

        return view;
    }

    @RequestMapping( value = "/view/{id}", method = RequestMethod.GET )
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

        ModelAndView modelAndView = new ModelAndView( "RecipeEditTile" );
        User user = this.userCrudService.getCurrentUser();

        if ( user != null ) {
            List<Cookbook> cookbooks = this.cookbookManagementService.getCookbooksForUser( user.getId() )
                    .stream()
                    .map( cookbook -> new Cookbook( cookbook ) )
                    .collect( Collectors.toList() );

            CookbookOverview overview = new CookbookOverview( cookbooks );
            RecipeCookbook cookbook = new RecipeCookbook();
            cookbook.setRecipeId( id );

            modelAndView.addObject( "cookbook", cookbook );
            modelAndView.addObject( "cookbookOverview", overview );
        }
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
            newRecipe.setWorkingTime( recipe.getWorkingTime() );
            newRecipe.setRestTime( recipe.getRestTime() );
            newRecipe.setPreparation( recipe.getPreparation() );
            newRecipe.setCalories( recipe.getCalories() );
            newRecipe.setCookingTime( recipe.getCookingTime() );
            newRecipe.setDifficulty( recipe.getDifficulty() );
            newRecipe.setServing( recipe.getServing() );
            newRecipe.setShortDescription( recipe.getShortDescription() );
            newRecipe.setCreationDate( LocalDateTime.now() );
            try {
                newRecipe.setImageData( image.getBytes() );
            } catch ( IOException e ) {
                logger.error( "Error on uploading image. " +
                                "Image name: " + image.getName() +
                                ", Content type: " + image.getContentType() +
                                ", Recipe name: " + recipe.getName(),
                        e );
                throw new ImageUploadFailed( image.getName(), image.getContentType(), e );
            }

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
            ingredientCrudService.saveRecipeIngredient( current.getId(), ingredients );
            view = "redirect:/recipes";
        }
        return view;
    }

    @RequestMapping( value = "/rate", method = RequestMethod.POST )
    public String rateRecipe( @RequestParam( "id" ) int id, @RequestParam( "rating" ) byte rating ) {
        ratingService.rateRecipe( id, rating );
        String view = "redirect:/recipes/view/" + id;
        return view;
    }

    private boolean validateImage( MultipartFile image, List<String> validImageContentTypes ) {
        boolean isValid = true;

        for ( String contentType : validImageContentTypes ) {
            isValid &= image.getContentType().equals( contentType );
        }

        return isValid;
    }
}
