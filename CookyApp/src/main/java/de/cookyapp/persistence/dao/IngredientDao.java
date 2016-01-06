package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.HibernateSessionFactory;
import de.cookyapp.persistence.entities.IngredientEntity;
import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.viewmodel.Ingredient;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 29.11.2015.
 */
@Repository
public class IngredientDao extends GenericCookyDaoImplementation<IngredientEntity, Integer>{

    public IngredientDao () {
        super(IngredientEntity.class);
    }

    @Override
    protected void loadLazy(IngredientEntity persistentObject) {

    }

    public IngredientEntity getIngredientByName (String search) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = null;
        IngredientEntity ingredient = null;

        try {

            transaction = session.beginTransaction();
            //TODO createcriteria unique
            Query query = session.createQuery("from IngredientEntity as ingredient where ingredient.name = :name")
                    .setParameter("name", search);
            List ingredientResults = query.list();
            if ( ingredientResults != null && ingredientResults.size() > 0 ) {
                ingredient = (IngredientEntity)ingredientResults.get( 0 );
            }



            //TODO the uniqueResult throws an exception because the database is inconsistent. had to uncomment this for the demo
            //ingredient = (IngredientEntity)query.uniqueResult();
            transaction.commit();

        } catch ( HibernateException e ) {
            // TODO log the exception
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return ingredient;
    }
}
