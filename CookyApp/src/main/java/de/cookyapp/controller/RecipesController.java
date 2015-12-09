package de.cookyapp.controller;

import de.cookyapp.persistence.dao.IngredientDao;
import de.cookyapp.persistence.dao.RecipesDao;
import de.cookyapp.persistence.dao.UserDao;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.viewmodel.Ingredient;
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
import java.util.Iterator;

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
    public String handleEditRecipeFinish(@ModelAttribute("recipe") @Valid  Recipe recipe, BindingResult bindingResult) {
        String view;
        if (bindingResult.hasErrors()) {
            view = "recipes/editRecipe";
        } else {
            RecipesDao recipesDao = new RecipesDao();
            IngredientDao ingredientDao = new IngredientDao();
            RecipeEntity recipeEntity = recipesDao.loadWithLazyRelations(recipe.getId());
            Iterator<RecipeIngredientEntity> iterator = recipeEntity.getIngredients().iterator();
            for (Ingredient ingredient : recipe.getIngredients()) {
                RecipeIngredientEntity entity = iterator.next();
                //if (ingredient.getId() == entity.getId()) {
                    if (ingredientDao.getIngredientByName(ingredient.getName()) == null) {
                        entity.setUnit(ingredient.getUnit());
                        entity.setAmount(ingredient.getAmount());
                        entity.getIngredient().setName(ingredient.getName());
                    }
                    else {
                        IngredientEntity ingredientEntity = ingredientDao.getIngredientByName(ingredient.getName());
                        entity.setIngredient(ingredientEntity);
                        entity.setAmount(ingredient.getAmount());
                        entity.setUnit(ingredient.getUnit());
                    }
                    //TODO Hier muss das Ingredient aus der DB geladen werden, falls vorhanden: setzen. Ansonsten ein neues IngredientEntity anlegen.
                    //entity.getIngredient().setName(ingredient.getName());
                //}
            }

            recipeEntity.setName(recipe.getName());
            recipeEntity.setShortDescription(recipe.getShortDescription());
            recipeEntity.setCalories(recipe.getCalories());
            recipeEntity.setCookingTime(recipe.getCookingTime());
            recipeEntity.setWorkingTime(recipe.getWorkingTime());
            recipeEntity.setDifficulty(recipe.getDifficulty());
            recipeEntity.setServing(recipe.getServing());
            recipeEntity.setPreparation(recipe.getPreparation());
            recipeEntity.setImageFileName("http://placehold.it/320x200");
            //TODO andere werte von recipe in recipeEntity ändern!
            recipesDao.editRecipe(recipeEntity);
            view = "redirect:/recipes";
        }

        return view;
    }
    @RequestMapping("/goToEditRecipe")
    public ModelAndView handleEditRecipe (@RequestParam("id") int id) {
        RecipesDao recipesDao = new RecipesDao();
        Recipe recipe = new Recipe(recipesDao.getRecipeById(id));
        ModelAndView model = new ModelAndView("recipes/editRecipe", "recipe", recipe);
        return model;
    }

    @RequestMapping("/goToAddRecipe")
    public ModelAndView handleGoToRecipe() {
        ModelAndView model = new ModelAndView("recipes/addRecipe", "recipe", new Recipe());
        return model;
    }

    @RequestMapping(value = "/addRecipe")
    public String handleAddRecipe (@ModelAttribute("recipe") @Valid Recipe recipe, BindingResult bindingResult) {
        String view;
        if (bindingResult.hasErrors()) {
            view = "recipes/addRecipe";
        } else {
            RecipesDao recipesDao = new RecipesDao();
            UserDao userDao = new UserDao();
            IngredientDao ingredientDao = new IngredientDao();

            RecipeEntity recipeEntity = new RecipeEntity(recipe);

            recipeEntity.setAuthor(userDao.loadUserById(7));
            ArrayList<RecipeIngredientEntity> ingredientList = new ArrayList<RecipeIngredientEntity>();
            for (Ingredient ingredient : recipe.getIngredients()) {
                IngredientEntity current = new IngredientEntity();
                if (ingredientDao.getIngredientByName(ingredient.getName()) == null) {
                    current.setName(ingredient.getName());
                }
                else {
                    current = ingredientDao.getIngredientByName(ingredient.getName());
                }

                RecipeIngredientEntity recipeIngredientEntity = new RecipeIngredientEntity();
                recipeIngredientEntity.setIngredient(current);
                recipeIngredientEntity.setAmount(ingredient.getAmount());
                recipeIngredientEntity.setUnit(ingredient.getUnit());
                recipeIngredientEntity.setRecipe(recipeEntity);

                ingredientList.add(recipeIngredientEntity);
            }
            recipeEntity.setIngredients(ingredientList);
            recipeEntity.setImageFileName("http://placehold.it/320x200");
            recipesDao.addRecipe(recipeEntity);
            view = "redirect:/recipes";
        }
        return view;
    }
}
