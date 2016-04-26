package de.cookyapp.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.authentication.IUserAuthorization;
import de.cookyapp.enums.AccountState;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.IImageUploadService;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private IUserCrudService userCrudService;
    private IRecipeCrudService recipeCrudService;
    private IIngredientCrudService ingredientCrudService;
    private IImageUploadService imageService;
    private IAuthenticationFacade authentication;
    private IUserAuthorization userAuthorization;

    @Autowired
    ServletContext context;

    @Autowired
    public RecipeController( IUserCrudService userCrudService, IRecipeCrudService recipeCrudService, IIngredientCrudService ingredientCrudService, IAuthenticationFacade authenticationFacade, IUserAuthorization userAuthorization, IImageUploadService imageService ) {
        this.userCrudService = userCrudService;
        this.recipeCrudService = recipeCrudService;
        this.ingredientCrudService = ingredientCrudService;
        this.imageService = imageService;
        this.authentication = authenticationFacade;
        this.userAuthorization = userAuthorization;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView handleRecipes() throws IOException {
        ModelAndView model = new ModelAndView( "RecipeOverviewTile" );
        String path = generatePath();
        model.addObject( "recipesList", this.recipeCrudService.getAllRecipes( path ) );
        return model;
    }

    @RequestMapping( "/removeRecipe" )
    public String handleRemoveRecipe( @RequestParam( "id" ) int id ) {
        this.recipeCrudService.deleteRecipe( id );
        return "redirect:/recipes";
    }

    @RequestMapping( "/editRecipe" )
    public String handleEditRecipeFinish( @ModelAttribute( "recipe" ) @Valid Recipe recipe, BindingResult bindingResult ) {
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
                recipeDTO.setAuthor( userToUserEntity( userCrudService.getCurrentUser() ) );
                recipeCrudService.updateRecipe( recipeDTO );
                ingredientCrudService.saveRecipeIngredient( recipeDTO.getId(), ingredients );
            }
            view = "redirect:/recipes";
        }

        return view;
    }

    @RequestMapping( "/goToEditRecipe" )
    public ModelAndView handleEditRecipe( @RequestParam( "id" ) int id ) {
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
            newRecipe.setAuthor( userToUserEntity( userCrudService.getUserByID( 16 ) ) );
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

            de.cookyapp.service.dto.Recipe recipeWithImage = uploadImage( image, current );

            recipeCrudService.updateRecipe( recipeWithImage );
            ingredientCrudService.saveRecipeIngredient( current.getId(), ingredients );
            view = "redirect:/recipes";
        }
        return view;
    }

    private UserEntity userToUserEntity( User user ) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId( user.getId() );
        userEntity.setUsername( user.getUsername() );
        userEntity.setPassword( user.getPassword() );
        userEntity.setForename( user.getForename() );
        userEntity.setSurname( user.getSurname() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setGender( user.getGender() );
        userEntity.setBirthdate( user.getBirthdate() );
        userEntity.setRegistrationDate( user.getRegistrationDate() );
        userEntity.setLastLoginDate( user.getLastLoginDate() );
        userEntity.setAccountState( user.getAccountState() );
        userEntity.setAccountState( AccountState.REGISTERED );
        userEntity.setRegistrationDate( LocalDateTime.now() );

        return userEntity;
    }

    private void validateImage( MultipartFile image ) {
        if ( !image.getContentType().equals( "image/jpeg" ) && !image.getContentType().equals( "image/jpg" ) ) {
            throw new RuntimeException( "Only JPG images are accepted" );
        }
    }

    private de.cookyapp.service.dto.Recipe uploadImage( MultipartFile image, de.cookyapp.service.dto.Recipe current ) {
        de.cookyapp.service.dto.Recipe recipe = current;
        InputStream inputStream = null;
        BufferedImage bufferedImage = null;
        String imageGUID = java.util.UUID.randomUUID().toString() + ".jpg";
        String completePath = generatePath() + imageGUID;
        try {
            try {
                inputStream = image.getInputStream();
                bufferedImage = ImageIO.read( inputStream );
            } finally {
                if ( inputStream != null ) {
                    inputStream.close();
                }
            }
            ImageIO.write( bufferedImage, "jpg", new File( completePath ) );
            recipe = imageService.saveImage( current, bufferedImage );
        } catch ( Exception ex ) {
            ex.toString();
        }

        return recipe;
    }

    private String generatePath() {
        String path;
        String imagePath = "resources/images/recipes/";
        String realPath = context.getRealPath( "/" );
        if ( !new File( imagePath ).exists() ) {
            File file = new File( imagePath );
            file.mkdirs();
        }
        path = realPath + imagePath;
        return path;
    }
}
