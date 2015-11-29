package de.cookyapp.persistence;

import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by Dominik on 15.11.2015.
 */
public enum HibernateSessionFactory {
    INSTANCE;

    private org.hibernate.SessionFactory factory;

    HibernateSessionFactory() {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure( "hibernate.cfg.xml" )
                .build();

        Metadata metadata = new MetadataSources( standardServiceRegistry ).getMetadataBuilder().build();

        this.factory = metadata.getSessionFactoryBuilder().build();
    }

    //some comment
    public Session openSession() {
        return this.factory.openSession();
    }
}
