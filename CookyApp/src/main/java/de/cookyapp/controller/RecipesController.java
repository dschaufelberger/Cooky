package de.cookyapp.controller;

import de.cookyapp.persistence.dao.RecipesDao;
import de.cookyapp.persistence.entities.RecipeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Jasper on 27.11.2015.
 */

@Controller
@RequestMapping("/")
public class RecipesController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "/index.jsp";
    }

    @RequestMapping("/recipes")
    public ModelAndView handleRecipes () {
        RecipesDao recipesDao = new RecipesDao();
        ModelAndView model = new ModelAndView("recipes");
        model.addObject("recipesList", recipesDao.getAllRecipes());
        return model;
    }

    @RequestMapping("/removeRecipe")
    public ModelAndView handleRemoveRecipe (@RequestParam("id") int id) {
        RecipesDao recipesDao = new RecipesDao();
        ModelAndView model = new ModelAndView("recipes");
        recipesDao.deleteRecipeById(id);
        return model;
    }

    @RequestMapping("/editRecipeFinish")
    public ModelAndView handleEditRecipeFinish(@RequestParam("id") int id) {
        RecipesDao recipesDao = new RecipesDao();
        ModelAndView model = new ModelAndView("recipes");
        recipesDao.editRecipe(id);
        return model;
    }

    @RequestMapping("/editRecipe")
    public ModelAndView handleEditRecipe (@RequestParam("id") int id) {
        RecipesDao recipesDao = new RecipesDao();
        ModelAndView model = new ModelAndView("editRecipe");
        model.addObject("recipe", recipesDao.getRecipeById(id));
        model.addObject("ingredients", recipesDao.getRecipeIngredients(id));
        return model;
    }

    @RequestMapping("/goToAddRecipe")
    public ModelAndView handleGoToRecipe() {
        ModelAndView model = new ModelAndView("addRecipe");
        return model;
    }

    @RequestMapping("/addRecipe")
    public ModelAndView handleAddRecipe (@RequestParam("recipeName") String recipeName, @RequestParam("shortDescription") String shortDescription, @RequestParam("serving") short serving, @RequestParam("preparation") String preparation, @RequestParam("calories") short calories, @RequestParam("difficulty") String difficulty, @RequestParam("workingTime") int workingTime, @RequestParam("cookingTime") int cookingTime) {
        ModelAndView model = new ModelAndView("recipes");
        RecipesDao recipesDao = new RecipesDao();
        recipesDao.addRecipe(recipeName, shortDescription, serving, preparation, calories, difficulty, workingTime, cookingTime);
        return model;
    }
}
