package de.cookyapp.persistence.dao;

import java.util.Collection;
import java.util.List;

import de.cookyapp.persistence.entities.RecipeEntity;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

/**
 * Created by Dominik Schaufelberger on 02.12.2015.
 */
@Repository
public class RecipeDao extends GenericCookyDaoImplementation<RecipeEntity, Integer> {

    public RecipeDao() {
        super( RecipeEntity.class );
    }

    @Override
    protected void loadLazy( RecipeEntity persistentObject ) {
        Hibernate.initialize( persistentObject.getIngredients() );
    }
}
