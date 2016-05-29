package de.cookyapp.service.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.enums.RecipeDifficulty;
import de.cookyapp.service.services.interfaces.IRecipeUtilityService;
import org.springframework.stereotype.Service;

/**
 * Created by Dominik Schaufelberger on 29.05.2016.
 */
@Service
public class RecipeUtilityService implements IRecipeUtilityService {
    @Override
    public List<RecipeDifficulty> getAvailableDifficulties() {
        RecipeDifficulty[] values = RecipeDifficulty.values();
        ArrayList<RecipeDifficulty> difficulties = new ArrayList<>( values.length - 1 );
        Collections.addAll( difficulties, values );
        List<RecipeDifficulty> validDifficulties = difficulties.stream()
                .filter( difficulty -> difficulty != RecipeDifficulty.NONE )
                .collect( Collectors.toList() );

        return validDifficulties;
    }
}
