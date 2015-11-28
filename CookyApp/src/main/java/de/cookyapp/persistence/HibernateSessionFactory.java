package de.cookyapp.persistence;

import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Dominik on 15.11.2015.
 */
public enum HibernateSessionFactory {
    INSTANCE;

    private org.hibernate.SessionFactory factory;

    HibernateSessionFactory() {
        /*Configuration configuration = new Configuration().configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings( configuration.getProperties() ).build();
        this.factory = configuration.buildSessionFactory( registry );*/
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder(  )
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
