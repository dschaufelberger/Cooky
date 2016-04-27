package de.cookyapp.service.services;

import de.cookyapp.persistence.repositories.IRecipeCrudRepository;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.services.interfaces.IRecipeRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jasper on 26.04.2016.
 */
@Transactional
@Service
public class RecipeRatingService implements IRecipeRatingService {

    @Override
    public Recipe rateRecipe( Recipe recipe, byte rating ) {
        Recipe currentRecipe = null;
        if (recipe != null) {
            currentRecipe = recipe;
            byte currentRating = 0;
            int ratingCount = 0;
            byte newRating = 0;

            if (recipe.getRating() !=  0) {
                currentRating = recipe.getRating();
                ratingCount = recipe.getVoteCount();
                int multiplier = ratingCount;
                ratingCount++;
                int calculatedRating = 0;
                calculatedRating = ((currentRating * multiplier) + rating) / ratingCount;
                newRating = (byte) calculatedRating;
            } else {
                newRating = rating;
                ratingCount++;
            }
            currentRecipe.setRating( newRating );
            currentRecipe.setVoteCount( ratingCount );
        }
        return currentRecipe;
    }
}
