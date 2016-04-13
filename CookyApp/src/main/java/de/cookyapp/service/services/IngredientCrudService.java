package de.cookyapp.service.services;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.authentication.IUserAuthorization;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.repositories.IIngredientCrudRepository;
import de.cookyapp.service.dto.Ingredient;
import de.cookyapp.service.services.interfaces.IIngredientCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jasper on 12.04.2016.
 */
@Transactional
@Service
public class IngredientCrudService implements IIngredientCrudService {
    private IIngredientCrudRepository ingredientCrudRepository;
    private IAuthenticationFacade authentication;
    private IUserAuthorization userAuthorization;

    @Autowired
    public IngredientCrudService (IIngredientCrudRepository ingredientCrudRepository, IAuthenticationFacade authentication, IUserAuthorization authorization) {
        this.ingredientCrudRepository = ingredientCrudRepository;
        this.authentication = authentication;
        this.userAuthorization = authorization;
    }

    @Override
    public void deleteIngredient(int ingredientId) {
        deleteIngredientById(ingredientId);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setName(ingredient.getName());
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {

    }

    @Override
    public String getIngredientName(int ingredientId) {
        String ingredientName = "";
        IngredientEntity ingredientEntity = ingredientCrudRepository.findOne(ingredientId);
        if (ingredientEntity != null) {
            ingredientName = ingredientEntity.getName();
        }
        return ingredientName;
    }

    @Override
    public void save(Ingredient ingredient) {
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setId(ingredient.getId());
        ingredientEntity.setName(ingredient.getName());

        ingredientCrudRepository.save(ingredientEntity);
    }

    private void deleteIngredientById( int id ) {
        IngredientEntity ingredientEntity = this.ingredientCrudRepository.findOne( id );
        //Benutzer Abfrage
        this.ingredientCrudRepository.delete( ingredientEntity );
    }
}
