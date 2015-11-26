package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.HibernateSessionFactory;
import de.cookyapp.persistence.entities.IngredientEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 15.11.2015.
 */
public class IngredientDao {


    public void insertIngredient( IngredientEntity ingredient ) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate( ingredient );
        transaction.commit();
        session.close();
    }

    public IngredientEntity getIngredientById( int id ) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        IngredientEntity ingredient = (IngredientEntity) session.get(IngredientEntity.class, id);
        transaction.commit();
        return ingredient;
    }

    public List<IngredientEntity> getAllIngredients () {
        List <IngredientEntity> ingredientList = new ArrayList<IngredientEntity>();
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        ingredientList = session.createQuery("from IngredientEntity ").list();
        for (IngredientEntity in : ingredientList) {
            System.out.println(in.getName());
        }
        transaction.commit();
        return ingredientList;
    }

    public void removeIngredient( int id ) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        IngredientEntity ingredient = getIngredientById(id);
        session.delete(ingredient);
        transaction.commit();
    }

    public void editIngredient(int id, String name) {
        Session session = HibernateSessionFactory.INSTANCE.openSession();
        Transaction transaction = session.beginTransaction();
        IngredientEntity ingredient = getIngredientById(id);
        ingredient.setName(name);
        session.update(ingredient);
        transaction.commit();
    }
}