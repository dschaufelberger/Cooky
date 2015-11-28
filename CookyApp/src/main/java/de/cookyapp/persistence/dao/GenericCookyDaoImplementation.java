package de.cookyapp.persistence.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.cookyapp.persistence.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Dominik Schaufelberger on 28.11.2015.
 */
public abstract class GenericCookyDaoImplementation <T, PK extends Serializable> implements GenericCookyDAO<T, PK> {
    private Class<T> clazz;

    private HibernateSessionFactory sessionFactory;

    public GenericCookyDaoImplementation( Class<T> clazz ) {
        this.clazz = clazz;
        this.sessionFactory = HibernateSessionFactory.INSTANCE;
    }

    @Override
    public PK save( T transientObject ) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        PK primaryKey = null;

        try {
            transaction = session.beginTransaction();

            primaryKey = (PK) session.save( transientObject );

            transaction.commit();
        } catch ( HibernateException e ) {
            transaction.rollback();

            // TODO log the exception
            throw e;
        } finally {
            session.close();
        }

        return primaryKey;
    }

    @Override
    public T load( PK id ) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        T persistentObject = null;

        try {
            transaction = session.beginTransaction();

            persistentObject = session.get( this.clazz, id );

            transaction.commit();
        } catch ( HibernateException e ) {
            transaction.rollback();

            // TODO log the exception
            throw e;
        } finally {
            session.close();
        }

        return persistentObject;
    }

    @Override
    public List<T> loadAll() {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        List<T> persistentObjects = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            persistentObjects = session.createCriteria( this.clazz ).list();

            transaction.commit();
        } catch ( HibernateException e ) {
            transaction.rollback();

            // TODO log the exception
            throw e;
        } finally {
            session.close();
        }

        return persistentObjects;
    }

    @Override
    public void update( T detachedObject ) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.update( detachedObject );

            transaction.commit();
        } catch ( HibernateException e ) {
            transaction.rollback();

            // TODO log the exception
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void remove( T persistentObject ) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.delete( persistentObject );

            transaction.commit();
        } catch ( HibernateException e ) {
            transaction.rollback();

            // TODO log the exception
            throw e;
        } finally {
            session.close();
        }
    }
}
