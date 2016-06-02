package de.cookyapp.service.mocks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;

/**
 * Created by Mario on 03.05.2016.
 */
public class UserRepositoryMock implements IUserCrudRepository {
    private LinkedList<UserEntity> users = new LinkedList<>();
    private int idCounter = 0;

    public UserRepositoryMock() {
    }

    public UserRepositoryMock( LinkedList<UserEntity> users ) {
        this.users = users;
    }

    public LinkedList<UserEntity> getUsers() {
        return users;
    }

    public void setUsers( LinkedList<UserEntity> users ) {
        for ( UserEntity user : users ) {
            this.users.add( createCopy( user ) );
        }
    }

    @Override
    public void delete( UserEntity deleted ) {
        this.users.remove( deleted );
    }

    @Override
    public void delete( Iterable<? extends UserEntity> entities ) {
        for ( UserEntity entity : entities ) {
            delete( entity );
        }
    }

    @Override
    public List<UserEntity> findAll() {
        return this.users;
    }

    @Override
    public UserEntity findOne( Integer userId ) {
        for ( UserEntity user : users ) {
            if ( user.getId() == userId ) {
                return user;
            }
        }

        return null;
    }

    @Override
    public UserEntity save( UserEntity persisted ) {
        UserEntity user = createCopy( persisted );

        if ( !this.users.remove( user ) ) {
            user.setId( idCounter++ );
        }
        this.users.add( user );

        return user;
    }

    @Override
    public <S extends UserEntity> Iterable<S> save( Iterable<S> persistedEntities ) {
        List<S> entities = new ArrayList<>();

        for ( S persistedEntity : persistedEntities ) {
            entities.add( (S) save( persistedEntity ) );
        }

        return entities;
    }

    @Override
    public UserEntity findByUsername( String username ) {
        for ( UserEntity user : users ) {
            if ( user.getUsername().equals( username ) ) {
                return user;
            }
        }

        return null;
    }

    @Override
    public List<UserEntity> findByUsernameContaining( String username ) {
        List<UserEntity> result = new LinkedList<>();

        for ( UserEntity user : users ) {
            if ( user.getUsername().contains( username ) ) {
                result.add( user );
            }
        }

        return result;
    }

    private UserEntity createCopy( UserEntity entity ) {
        UserEntity copy = new UserEntity();
        copy.setId( entity.getId() );
        copy.setForename( entity.getForename() );
        copy.setSurname( entity.getSurname() );
        copy.setUsername( entity.getUsername() );
        copy.setPassword( entity.getPassword() );
        copy.setEmail( entity.getEmail() );
        copy.setLastLoginDate( entity.getLastLoginDate() );
        copy.setRegistrationDate( entity.getRegistrationDate() );
        copy.setGender( entity.getGender() );
        copy.setAddress( entity.getAddress() );
        copy.setBirthdate( entity.getBirthdate() );
        copy.setAccountState( entity.getAccountState() );

        return copy;
    }
}
