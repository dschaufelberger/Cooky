package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.HibernateSessionFactory;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jasper on 27.11.2015.
 */
public class RecipesDao extends GenericCookyDaoImplementation<RecipeEntity, Integer> {

    public RecipesDao() {
        super(RecipeEntity.class);
    }

    public RecipeEntity getRecipeById(int id) {
        return this.load(id);
    }

    public List<RecipeEntity> getAllRecipes() {
        List<RecipeEntity> recipes = this.loadAll();
        return recipes;
    }

    public void deleteRecipeById(int id) {
        RecipeEntity recipe = this.load(id);
        this.remove(recipe);
    }

    public void editRecipe(int id, String name, String shortDescription, String preparation, int workingTime, int cookingTime) {
        RecipeEntity recipe = this.load(id);
        recipe.setName(name);
        recipe.setShortDescription(shortDescription);
        recipe.setPreparation(preparation);
        recipe.setWorkingTime(workingTime);
        recipe.setCookingTime(cookingTime);
        this.update(recipe);
    }

    public void editRecipe(RecipeEntity recipe) {
        //recipe.setAuthorId(3);
        this.update(recipe);
    }

    public void addRecipe(String name, String shortDescription, String preparation, int workingTime, int cookingTime) {
        RecipeEntity recipe = new RecipeEntity();
        recipe.setName(name);
        recipe.setShortDescription(shortDescription);
        //recipe.setServing(serving);
        recipe.setPreparation(preparation);
        //recipe.setCalories(calories);
        //recipe.setDifficulty(difficulty);
        recipe.setWorkingTime(workingTime);
        recipe.setCookingTime(cookingTime);
        //recipe.setAuthorId(3);
        //IngredientEntity ingredient = new IngredientEntity();
        //ingredient.setName(ingredientName);
        //Wie Foreign Key setzen?
        this.save(recipe);

    }

    public void addRecipe(RecipeEntity recipe) {
        this.save(recipe);
    }

    @Override
    protected void loadLazy(RecipeEntity persistentObject) {
        Hibernate.initialize(persistentObject.getIngredients());
    }
}
