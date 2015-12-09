package de.cookyapp.persistence.dao;

import de.cookyapp.persistence.HibernateSessionFactory;
import de.cookyapp.persistence.entities.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Repository;

/**
 * This class inherits from the GenericCookyDaoImplementation abstract class.
 * Since there is a basic implementation for all the CRUD operations in the abstract super class, just the super construtor
 * needs to be called with the entity class to persist as parameter.
 * <p>
 * Created by Dominik Schaufelberger on 28.11.2015.
 */
@Repository
public class UserDao extends GenericCookyDaoImplementation<UserEntity, Integer> {

    public UserDao() {
        super( UserEntity.class );
    }


    public void editUser( int id, String forename, String surname, String email ) {
        UserEntity user = this.load( id );
        user.setForename( forename );
        user.setSurname( surname );
        user.setEmail( email );
        this.update( user );
    }

    public UserEntity loadUserByUsername(String userName) {
        HibernateSessionFactory sessionFactory;
        sessionFactory = HibernateSessionFactory.INSTANCE;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        UserEntity user = null;

        try {
            transaction = session.beginTransaction();
            user = (UserEntity) session.createQuery( "from UserEntity where username =:userNameText" ).setString("userNameText", userName).uniqueResult();
            transaction.commit();

        }catch (Exception e){
            transaction.rollback();
            // TODO log the exception
            throw e;
        }finally {
            session.close();
        }
        return user;
    }

    @Override
    protected void loadLazy( UserEntity persistentObject ) {

    }
}
