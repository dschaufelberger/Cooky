package de.cookyapp.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Mario on 15.11.2015.
 */
public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings( configuration.getProperties() ).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory( serviceRegistry );
        } catch ( Throwable ex ) {
            throw new ExceptionInInitializerError( ex );
        }
        /*final Session session = getSession();
        try {
            System.out.println( "querying all the managed entities..." );
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for ( Object key : metadataMap.keySet() ) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get( key );
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery( "from " + entityName );
                System.out.println( "executing: " + query.getQueryString() );
                for ( Object o : query.list() ) {
                    System.out.println( "  " + o );
                }
            }
        } finally {
            session.close();
        }*/
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

}