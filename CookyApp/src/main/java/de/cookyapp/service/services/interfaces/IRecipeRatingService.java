package de.cookyapp.service.services.interfaces;

import de.cookyapp.service.dto.Recipe;

/**
 * Created by Jasper on 26.04.2016.
 */
public interface IRecipeRatingService {

    public Recipe rateRecipe ( Recipe recipe, byte rating );
}
