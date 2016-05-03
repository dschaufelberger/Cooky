package de.cookyapp.service.services.interfaces;

import de.cookyapp.service.dto.Recipe;

/**
 * Created by Jasper on 26.04.2016.
 */
public interface IRecipeRatingService {

    public void rateRecipe ( int recipeId, byte rating );
}
