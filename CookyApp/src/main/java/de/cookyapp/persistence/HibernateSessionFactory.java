package de.cookyapp.persistence;

import org.hibernate.Session;
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
        Configuration configuration = new Configuration().configure();
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings( configuration.getProperties() ).build();
        this.factory = configuration.buildSessionFactory( registry );
    }

    public Session openSession() {
        return this.factory.openSession();
    }
}
