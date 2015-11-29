package de.cookyapp.controller;

import de.cookyapp.persistence.dao.IngredientDao;
import de.cookyapp.persistence.dao.RecipesDao;
import org.springframework.stereotype.Controller;
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
    public String handleRemoveRecipe (@RequestParam("id") int id) {
        RecipesDao recipesDao = new RecipesDao();
        recipesDao.deleteRecipeById(id);
        return "redirect:/recipes";
    }

    @RequestMapping("/editRecipeFinish")
    public String handleEditRecipeFinish(@RequestParam("id") int id, @RequestParam("editName") String name, @RequestParam("editShortDescription") String shortDescription, @RequestParam("editPreparation") String preparation, @RequestParam("editWorkingTime") int workingTime, @RequestParam("editCookingTime") int cookingTime) {
        RecipesDao recipesDao = new RecipesDao();
        recipesDao.editRecipe(id, name, shortDescription, preparation, workingTime, cookingTime);
        return "redirect:/recipes";
    }

    @RequestMapping("/editRecipe")
    public ModelAndView handleEditRecipe (@RequestParam("id") int id) {
        RecipesDao recipesDao = new RecipesDao();
        ModelAndView model = new ModelAndView("editRecipe");
        model.addObject("recipe", recipesDao.getRecipeById(id));
        return model;
    }

    @RequestMapping("/goToAddRecipe")
    public ModelAndView handleGoToRecipe() {
        ModelAndView model = new ModelAndView("addRecipe");
        return model;
    }

    @RequestMapping("/addRecipe")
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
    }
}
