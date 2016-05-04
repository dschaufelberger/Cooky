package de.cookyapp.service;

import java.util.ArrayList;
import java.util.List;

import de.cookyapp.persistence.entities.AddressEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.service.dto.Address;
import de.cookyapp.service.mocks.AddressRepositoryMock;
import de.cookyapp.service.mocks.AuthenticationMock;
import de.cookyapp.service.mocks.UserRepositoryMock;
import de.cookyapp.service.services.AddressService;
import de.cookyapp.service.services.interfaces.IAddressService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Mario on 03.05.2016.
 */
public class AddressServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private AddressRepositoryMock repositoryMock;
    private IAddressService service;
    private UserRepositoryMock userRepositoryMock;
    private AuthenticationMock authentication;
    private UserEntity userDummy;
    private Address newAddress;


    @Before
    public void setUp() throws Exception {
        repositoryMock = new AddressRepositoryMock();
        userRepositoryMock = new UserRepositoryMock();
        //Authentication
        authentication = new AuthenticationMock( "CookyTester" );
        this.service = new AddressService( repositoryMock, userRepositoryMock, authentication );

        //initialize User
        userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );
        userDummy.setId( 1 );

        //save user
        this.userRepositoryMock.save( userDummy );

        //initialize Address
        newAddress = new Address();
        newAddress.setStreet( "Teststrasse" );
        newAddress.setPostcode( "7777" );
        newAddress.setHouseNumber( "9" );
        newAddress.setCity( "Testdorf" );
    }

    @Test
    public void testCreateAddressForUser() throws Exception {
        //act
        this.service.createAddressForUser( userDummy.getId(), newAddress );
        //assert
        Assert.assertEquals( 1, this.repositoryMock.getEntities().size() );
    }

    @Test
    public void testRemoveAddressFromUser() throws Exception {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity( newAddress.getCity() );
        addressEntity.setHouseNumber( newAddress.getHouseNumber() );
        addressEntity.setPostcode( newAddress.getPostcode() );
        addressEntity.setStreet( newAddress.getStreet() );
        addressEntity.setId( 1 );
        //set up list with address entities
        List<AddressEntity> entityList = new ArrayList<>();
        entityList.add( addressEntity );
        this.repositoryMock.setEntities( entityList );

        //act
        this.service.removeAddressFromUser( userDummy.getId(), 1 );
        //assert
        Assert.assertEquals( 0, this.repositoryMock.getEntities().size() );
    }

    @Test
    public void updateAddress() throws Exception {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity( newAddress.getCity() );
        addressEntity.setHouseNumber( newAddress.getHouseNumber() );
        addressEntity.setPostcode( newAddress.getPostcode() );
        addressEntity.setStreet( newAddress.getStreet() );
        addressEntity.setId( 1 );
        //set up list wth address entities
        List<AddressEntity> entityList = new ArrayList<>();
        entityList.add( addressEntity );
        this.repositoryMock.setEntities( entityList );
        //set Id for Address DTO
        newAddress.setId( 1 );
        //Update fields
        newAddress.setCity( "Testcity" );

        userDummy.setAddress( addressEntity );
        this.userRepositoryMock.save( userDummy );
        //act
        this.service.updateAddress( newAddress );

        Assert.assertEquals( "Testcity", this.repositoryMock.getEntities().get( 0 ).getCity() );
    }

    @Test
    public void getAddressForUser() throws Exception {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity( newAddress.getCity() );
        addressEntity.setHouseNumber( newAddress.getHouseNumber() );
        addressEntity.setPostcode( newAddress.getPostcode() );
        addressEntity.setStreet( newAddress.getStreet() );
        addressEntity.setId( 1 );
        //set up list wth address entities
        List<AddressEntity> entityList = new ArrayList<>();
        entityList.add( addressEntity );
        this.repositoryMock.setEntities( entityList );
        //set Id for Address DTO
        newAddress.setId( 1 );
        // set up user
        userDummy.setAddress( addressEntity );
        this.userRepositoryMock.save( userDummy );

        Address address = this.service.getAddressForUser( userDummy.getId() );

        Assert.assertEquals( newAddress.getId(), address.getId() );
    }
}