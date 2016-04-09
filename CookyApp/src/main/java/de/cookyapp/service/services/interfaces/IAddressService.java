package de.cookyapp.service.services.interfaces;

import de.cookyapp.service.dto.Address;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
public interface IAddressService {
    void createAddressForUser( int userID, Address address );

    void removeAddressFromUser( int userID, int addressID );

    void updateAddress( Address address );

    Address getAddress( int addressID );

    Address getAddressForUser( int userID );
}
