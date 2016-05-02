package de.cookyapp.service.services;

import de.cookyapp.persistence.entities.AddressEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.IAddressCrudRepository;
import de.cookyapp.persistence.repositories.IUserCrudRepository;
import de.cookyapp.service.dto.Address;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.IAddressService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mario Kaiser on 25.04.2016.
 *
 */
@Transactional
@Service
public class AddressService implements IAddressService {
    private IAddressCrudRepository addressCrudRepository;
    private IUserCrudRepository userCrudRepository;

    @Autowired
    public AddressService(IAddressCrudRepository addressCrudRepository, IUserCrudRepository userCrudRepository) {
        this.addressCrudRepository = addressCrudRepository;
    }

    @Override
    public void createAddressForUser(int userID, Address address) {
        AddressEntity addressEntity = this.addressCrudRepository.findOne(address.getId());
        addressEntity.setCity(address.getCity());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setPostcode(address.getPostcode());
        addressEntity.setHouseNumber(address.getHouseNumber());
        this.addressCrudRepository.save(addressEntity);

        UserEntity userEntity = this.userCrudRepository.findOne(userID);
        userEntity.setAddress(addressEntity);
        this.userCrudRepository.save(userEntity);
    }

    @Override
    public void removeAddressFromUser(int userID, int addressID) {
        AddressEntity addressEntity = this.addressCrudRepository.findOne(addressID);
        this.addressCrudRepository.delete(addressEntity);
        UserEntity userEntity = this.userCrudRepository.findOne(userID);
        userEntity.setAddress(null);
        this.addressCrudRepository.delete( addressEntity );
        this.userCrudRepository.save(userEntity);
    }

    @Override
    public void updateAddress(Address address) {
        AddressEntity addressEntity = this.addressCrudRepository.findOne(address.getId());
        if (addressEntity != null) {
            addressEntity.setStreet(address.getStreet());
            addressEntity.setPostcode(address.getPostcode());
            addressEntity.setHouseNumber(address.getHouseNumber());
            addressEntity.setCity(address.getCity());
            this.addressCrudRepository.save(addressEntity);
        }
    }

    @Override
    public Address getAddress(int addressID) {
        AddressEntity addressEntity = this.addressCrudRepository.findOne(addressID);
        Address address = this.getAddressIfExistant(addressEntity);
        return address;
    }

    @Override
    public Address getAddressForUser(int userID) {
        UserEntity userEntity = this.userCrudRepository.findOne(userID);
        AddressEntity addressEntity = userEntity.getAddress();
        Address address = this.getAddressIfExistant(addressEntity);
        return address;
    }

    private Address getAddressIfExistant(AddressEntity addressEntity) {
        return addressEntity == null ? null : new Address(addressEntity);
    }

}
