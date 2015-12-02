package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;

import java.util.List;

/**
 * Created by Jasper on 29.11.2015.
 */
public class IngredientDao extends GenericCookyDaoImplementation<IngredientEntity, Integer>{

    public IngredientDao () {
        super(IngredientEntity.class);
    }

    public IngredientEntity getIngredientById (int id) {
        return this.load(id);
    }

    public List<IngredientEntity> getAllIngredients () {
        return this.loadAll();
    }

    public void addIngredient (String name) {
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setName(name);
        this.save(ingredient);
    }

    public void updateIngredient (int id, String name) {
        IngredientEntity ingredient = this.getIngredientById(id);
        ingredient.setName(name);
        this.update(ingredient);
    }

    @Override
    protected void loadLazy(IngredientEntity persistentObject) {

    }
}
