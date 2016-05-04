package de.cookyapp.service.mocks;

import java.util.List;

import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.IUserCrudRepository;

/**
 * Created by Mario on 03.05.2016.
 */
//UP TO NOW !! only for JUnit tests to test AddressService
public class UserRepositoryMock implements IUserCrudRepository {

    private UserEntity entity;

    @Override
    public void delete( UserEntity deleted ) {

    }

    @Override
    public void delete( Iterable<? extends UserEntity> entities ) {

    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }

    //UP TO NOW !! only for JUnit tests to test AddressService
    @Override
    public UserEntity findOne( Integer userId ) {
        return this.createCopy( this.entity );
    }

    @Override
    public UserEntity save( UserEntity persisted ) {
        this.entity = persisted;
        return this.entity;
    }

    @Override
    public <S extends UserEntity> Iterable<S> save( Iterable<S> persistedEntities ) {
        return null;
    }

    @Override
    public UserEntity findByUsername( String username ) {
        return entity;
    }

    private UserEntity createCopy( UserEntity entity ) {
        UserEntity copy = new UserEntity();

        //not all attributes - only for test case
        copy.setForename( entity.getForename() );
        copy.setId( entity.getId() );
        copy.setSurname( entity.getSurname() );
        copy.setAddress( entity.getAddress() );
        copy.setBirthdate( entity.getBirthdate() );
        copy.setUsername( entity.getUsername() );


        return copy;
    }
}
