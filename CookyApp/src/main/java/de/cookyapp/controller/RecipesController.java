package de.cookyapp.controller;

import de.cookyapp.persistence.dao.RecipesDao;
import de.cookyapp.persistence.dao.UserDao;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.viewmodel.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jasper on 27.11.2015.
 */

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRecipes () {
        RecipesDao recipesDao = new RecipesDao();
        ModelAndView model = new ModelAndView("recipes/recipes");
        model.addObject("recipesList", recipesDao.getAllRecipes());
        return model;
    }

    @RequestMapping("/removeRecipe")
    public String handleRemoveRecipe (@RequestParam("id") int id) {
        RecipesDao recipesDao = new RecipesDao();
        recipesDao.deleteRecipeById(id);
        return "redirect:/recipes";
    }

    /*@RequestMapping("/editRecipeFinish")
    public String handleEditRecipeFinish(@RequestParam("id") int id, @RequestParam("editName") String name, @RequestParam("editShortDescription") String shortDescription, @RequestParam("editPreparation") String preparation, @RequestParam("editWorkingTime") int workingTime, @RequestParam("editCookingTime") int cookingTime) {
        RecipesDao recipesDao = new RecipesDao();
        recipesDao.editRecipe(id, name, shortDescription, preparation, workingTime, cookingTime);
        return "redirect:/recipes";
    }
    */

    @RequestMapping("/editRecipe")
    public String handleEditRecipeFinish(@ModelAttribute("recipe") @Valid  RecipeEntity recipe, BindingResult bindingResult) {
        String view;
        if (bindingResult.hasErrors()) {
            view = "editRecipe";
        } else {
            RecipesDao recipesDao = new RecipesDao();
            recipesDao.editRecipe(recipe);
            view = "redirect:/recipes";
        }

        return view;
    }
    @RequestMapping("/goToEditRecipe")
    public ModelAndView handleEditRecipe (@RequestParam("id") int id) {
        RecipesDao recipesDao = new RecipesDao();
        //ModelAndView model = new ModelAndView("editRecipe");
        //model.addObject("recipe", recipesDao.getRecipeById(id));
        ModelAndView model = new ModelAndView("recipes/editRecipe", "recipe", new Recipe());
        if (recipesDao.getRecipeById(id).getIngredients() != null) {
            model.addObject("recipeForId", recipesDao.loadWithLazyRelations(id));
        }
        return model;
    }

    @RequestMapping("/goToAddRecipe")
    public ModelAndView handleGoToRecipe() {
        ModelAndView model = new ModelAndView("recipes/addRecipe", "recipe", new Recipe());
        return model;
    }

    /*@RequestMapping("/addRecipe")
    public String handleAddRecipe (@RequestParam("recipeName") String recipeName, @RequestParam("shortDescription") String shortDescription, @RequestParam("preparation") String preparation, @RequestParam("workingTime") int workingTime, @RequestParam("cookingTime") int cookingTime, @RequestParam("ingredient") String[] ingredients) {
        RecipesDao recipesDao = new RecipesDao();
        recipesDao.addRecipe(recipeName, shortDescription, preparation, workingTime, cookingTime);
        IngredientDao ingredientDao = new IngredientDao();
        for(String ingredient : ingredients) {
            System.out.println(ingredient);
            if (!("").equals(ingredient)) {
                ingredientDao.addIngredient(ingredient);
            }
        }
        return "redirect:/recipes";
    } */

    @RequestMapping(value = "/addRecipe")
    public String handleAddRecipe (@ModelAttribute("recipe") @Valid Recipe recipe, BindingResult bindingResult, @RequestParam("ingredient") String[] ingredients) {
        String view;
        if (bindingResult.hasErrors()) {
            view = "recipes/addRecipe";
        } else {
            RecipesDao recipesDao = new RecipesDao();
            UserDao userDao = new UserDao();
            RecipeEntity recipeEntity = new RecipeEntity(recipe);
            recipeEntity.setAuthor(userDao.loadUserById(7));
            ArrayList<RecipeIngredientEntity> ingredientList = new ArrayList<RecipeIngredientEntity>();
            System.out.println("Vor For: " + ingredients.length);
            for (String ingredient : ingredients) {
                if (!("").equals(ingredient)) {
                    RecipeIngredientEntity currentRecipeIngredient = new RecipeIngredientEntity();
                    IngredientEntity ingredientEntity = new IngredientEntity();
                    ingredientEntity.setName(ingredient);
                    currentRecipeIngredient.setRecipe(recipeEntity);
                    currentRecipeIngredient.setIngredient(ingredientEntity);
                    currentRecipeIngredient.setAmount("300");
                    currentRecipeIngredient.setUnit("g");
                    ingredientList.add(currentRecipeIngredient);
                    System.out.println(ingredient);
                }
            }
            recipeEntity.setIngredients(ingredientList);
            recipesDao.addRecipe(recipeEntity);
            view = "redirect:/recipes";
        }
        return view;
    }
}
