package de.cookyapp.persistence.repositories;

import de.cookyapp.persistence.entities.IngredientEntity;

import java.util.List;


/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
public interface IIngredientCrudRepository extends IBaseCrudRepository<IngredientEntity, Integer> {
    List<IngredientEntity> findByName(String ingredient);
}
