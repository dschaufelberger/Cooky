package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.service.dto.Recipe;

/**
 * Created by Mario on 16.05.2016.
 */
public interface IRecipeOfTheMonthService {

    List<Recipe> getRecipeOfTheMonth();

    List<Recipe> updateRecipeOfTheMonth();


}
