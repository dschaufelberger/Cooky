package de.cookyapp.persistence.repositories.app;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * Created by Dominik Schaufelberger on 03.04.2016.
 */
@NoRepositoryBean
public interface IBaseCrudRepository <T, ID extends Serializable> extends Repository<T, ID> {
    void delete( T deleted );

    void delete( Iterable<? extends T> entities );

    List<T> findAll();

    T findOne( ID id );

    T save( T persisted );

    <S extends T> Iterable<S> save( Iterable<S> persistedEntities );
}
