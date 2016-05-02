package de.cookyapp.service.services;

import java.util.List;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.repositories.ICookbookRepository;
import de.cookyapp.service.services.interfaces.ICookbookContentService;
import de.cookyapp.service.services.interfaces.IRecipeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 02.05.2016.
 */
@Transactional
@Service
public class CookbookContentService implements ICookbookContentService {
    private IRecipeCrudService recipeCrudService;
    private ICookbookRepository cookbookRepository;
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    public CookbookContentService( IRecipeCrudService recipeCrudService, ICookbookRepository cookbookRepository, IAuthenticationFacade authenticationFacade ) {
        this.recipeCrudService = recipeCrudService;
        this.cookbookRepository = cookbookRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void addRecipeToCookbook( int cookbookID, int recipeID ) {

    }

    @Override
    public void addRecipesToCookbook( int cookbookID, List<Integer> recipesIDs ) {

    }

    @Override
    public void removeRecipeFromCookbook( int cookbookID, int recipeID ) {

    }

    @Override
    public void removeAllRecipesFromCookbook( int cookbookID ) {

    }
}
