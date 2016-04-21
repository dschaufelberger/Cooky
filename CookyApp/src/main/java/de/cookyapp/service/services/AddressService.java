package de.cookyapp.service.services;

import de.cookyapp.service.dto.Address;
import de.cookyapp.service.services.interfaces.IAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
@Transactional
@Service
public class AddressService implements IAddressService {
    @Override
    public void createAddressForUser( int userID, Address address ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateAddress( Address address ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Address getAddress( int addressID ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Address getAddressForUser( int userID ) {
        throw new UnsupportedOperationException();
    }
}
