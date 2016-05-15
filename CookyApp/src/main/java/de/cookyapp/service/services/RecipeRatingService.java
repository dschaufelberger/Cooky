package de.cookyapp.service.services;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;
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

    private IRecipeCrudRepository recipeCrudRepository;

    @Autowired
    public RecipeRatingService( IRecipeCrudRepository recipeCrudRepository ) {
        this.recipeCrudRepository = recipeCrudRepository;
    }

    @Override
    public void rateRecipe( int recipeId, byte rating ) {
        //TODO check if the recipe is in a public cookbook [Jasper]
        RecipeEntity currentRecipe = recipeCrudRepository.findOne( recipeId );
        if ( currentRecipe != null ) {
            byte currentRating = 0;
            int ratingCount = 0;
            byte newRating = 0;

            if ( currentRecipe.getRating() != 0 ) {
                currentRating = currentRecipe.getRating();
                ratingCount = currentRecipe.getVoteCount();
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

            recipeCrudRepository.save( currentRecipe );
        }
    }
}
