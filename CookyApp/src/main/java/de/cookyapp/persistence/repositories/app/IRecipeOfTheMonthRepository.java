package de.cookyapp.persistence.repositories.app;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.entities.RecipeOfTheMonthEntity;

/**
 * Created by Mario on 16.05.2016.
 */
public interface IRecipeOfTheMonthRepository extends IBaseCrudRepository<RecipeOfTheMonthEntity, Integer>{

    RecipeOfTheMonthEntity findFirstByOrderByUpdatedDesc();

}
