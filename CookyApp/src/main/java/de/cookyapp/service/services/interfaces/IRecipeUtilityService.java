package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.enums.RecipeDifficulty;

/**
 * Created by Dominik Schaufelberger on 29.05.2016.
 */
public interface IRecipeUtilityService {
    List<RecipeDifficulty> getAvailableDifficulties();
}
