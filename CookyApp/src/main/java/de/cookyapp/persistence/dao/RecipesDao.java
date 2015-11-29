package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.HibernateSessionFactory;
import de.cookyapp.persistence.entities.RecipeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Jasper on 27.11.2015.
 */
public class RecipesDao extends GenericCookyDaoImplementation<RecipeEntity, Integer> {

    public RecipesDao() {
        super(RecipeEntity.class);
    }

    public RecipeEntity getRecipeById(int id) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        RecipeEntity recipe = (RecipeEntity) session.get(RecipeEntity.class, id);
        transaction.commit();
        return recipe;
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
        recipe.setAuthorId(3);
        //IngredientEntity ingredient = new IngredientEntity();
        //ingredient.setName(ingredientName);
        //Wie Foreign Key setzen?
        this.save(recipe);

    }
}
