package de.cookyapp.service.services;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.AddressEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.app.IAddressCrudRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.service.dto.Address;
import de.cookyapp.service.exceptions.InvalidUserId;
import de.cookyapp.service.exceptions.UserNotAuthorized;
import de.cookyapp.service.services.interfaces.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
@Transactional
@Service
public class AddressService implements IAddressService {
    private IAddressCrudRepository addressCrudRepository;
    private IUserCrudRepository userCrudRepository;
    private IAuthenticationFacade authentication;

    @Autowired
    public AddressService( IAddressCrudRepository addressCrudRepository, IUserCrudRepository userCrudRepository, IAuthenticationFacade authenticationFacade ) {
        this.userCrudRepository = userCrudRepository;
        this.addressCrudRepository = addressCrudRepository;
        this.authentication = authenticationFacade;
    }

    @Override
    public void createAddressForUser( int userID, Address address ) {
        UserEntity userEntity = this.userCrudRepository.findOne( userID );

        if ( userEntity != null ) {
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setCity( address.getCity() );
            addressEntity.setStreet( address.getStreet() );
            addressEntity.setPostcode( address.getPostcode() );
            addressEntity.setHouseNumber( address.getHouseNumber() );
            addressEntity = this.addressCrudRepository.save( addressEntity );

            userEntity.setAddress( addressEntity );
            this.userCrudRepository.save( userEntity );
        } else {
            throw new InvalidUserId( userID );
        }
    }

    @Override
    public void removeAddressFromUser( int userID, int addressID ) {
        AddressEntity addressEntity = this.addressCrudRepository.findOne( addressID );
        this.addressCrudRepository.delete( addressEntity );
        UserEntity userEntity = this.userCrudRepository.findOne( userID );
        userEntity.setAddress( null );
        this.addressCrudRepository.delete( addressEntity );
        this.userCrudRepository.save( userEntity );
    }

    @Override
    public void updateAddress( Address address ) {
        AddressEntity addressEntity = this.addressCrudRepository.findOne( address.getId() );

        if ( addressEntity != null ) {
            UserEntity userEntity = this.userCrudRepository.findByUsername( this.authentication.getAuthentication().getName() );

            if ( userEntity != null ) {
                boolean isAuthorized = addressEntity.equals( userEntity.getAddress() );

                if ( isAuthorized ) {
                    if ( addressEntity != null ) {
                        addressEntity.setStreet( address.getStreet() );
                        addressEntity.setPostcode( address.getPostcode() );
                        addressEntity.setHouseNumber( address.getHouseNumber() );
                        addressEntity.setCity( address.getCity() );
                        this.addressCrudRepository.save( addressEntity );
                    }
                }
            } else {
                throw new UserNotAuthorized();
            }
        }
    }

    @Override
    public Address getAddress( int addressID ) {
        AddressEntity addressEntity = this.addressCrudRepository.findOne( addressID );

        return getAddressIfExistant( addressEntity );
    }

    @Override
    public Address getAddressForUser( int userID ) {
        UserEntity userEntity = this.userCrudRepository.findOne( userID );

        if ( userEntity != null ) {
            boolean isAuthorized = userEntity.getUsername().equals( this.authentication.getAuthentication().getName() );

            if ( isAuthorized ) {
                AddressEntity addressEntity = userEntity.getAddress();
                Address address = this.getAddressIfExistant( addressEntity );
                return address;
            }
        } else {
            throw new InvalidUserId( userID );
        }

        return null;
    }

    private Address getAddressIfExistant( AddressEntity addressEntity ) {
        return addressEntity == null ? null : new Address( addressEntity );
    }

}
