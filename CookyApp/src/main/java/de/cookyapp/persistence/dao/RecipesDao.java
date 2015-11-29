package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.HibernateSessionFactory;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeIngredientEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 27.11.2015.
 */
public class RecipesDao {

    public RecipeEntity getRecipeById(int id) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        RecipeEntity recipe = (RecipeEntity) session.get(RecipeEntity.class, id);
        transaction.commit();
        return recipe;
    }

    public List<RecipeEntity> getAllRecipes() {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        List<RecipeEntity> recipeList = (List<RecipeEntity>) session.createQuery("from RecipeEntity").list();
        transaction.commit();
        return recipeList;
    }

    public void deleteRecipeById(int id) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        RecipeEntity recipe = getRecipeById(id);
        session.delete(recipe);
        transaction.commit();
    }

    public void editRecipe(int id) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        RecipeEntity recipe = getRecipeById(id);
        recipe.setName("Hallo Test Name");
        session.update(recipe);
        transaction.commit();
    }

    public List<RecipeIngredientEntity> getRecipeIngredients(int id) {
        List<RecipeIngredientEntity> recipeIngredients = new ArrayList<RecipeIngredientEntity>();
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        recipeIngredients = session.createQuery("from RecipeIngredientEntity  where RecipeIngredientEntity.recipeId = id").list();
        session.update(recipeIngredients);
        transaction.commit();
        return recipeIngredients;
    }

    public void addRecipe(String name, String shortDescription, int serving, String preparation, int calories, String difficulty, int workingTime, int cookingTime, String ingredientName) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        RecipeEntity recipe = new RecipeEntity();
        recipe.setName(name);
        recipe.setShortDescription(shortDescription);
        //recipe.setServing(serving);
        recipe.setPreparation(preparation);
        //recipe.setCalories(calories);
        recipe.setDifficulty(difficulty);
        recipe.setWorkingTime(workingTime);
        recipe.setCookingTime(cookingTime);
        //IngredientEntity ingredient = new IngredientEntity();
        //ingredient.setName(ingredientName);
        //Wie Foreign Key setzen?
        session.save(recipe);
        //session.save(ingredient);
        transaction.commit();
    }
}
