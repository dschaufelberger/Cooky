package de.cookyapp.service.services;

import java.util.List;

import de.cookyapp.service.dto.Address;
import de.cookyapp.service.services.interfaces.IAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
@Transactional
@Service
public class AddressService implements IAddressService {
    @Override
    public void createAddressForUser( int userID, Address address ) {
        throw new NotImplementedException();
    }

    @Override
    public void removeAddressFromUser( int userID, int addressID ) {
        throw new NotImplementedException();
    }

    @Override
    public void updateAddress( Address address ) {
        throw new NotImplementedException();
    }

    @Override
    public Address getAddress( int addressID ) {
        throw new NotImplementedException();
    }

    @Override
    public Address getAddressForUser( int userID ) {
        throw new NotImplementedException();
    }
}
