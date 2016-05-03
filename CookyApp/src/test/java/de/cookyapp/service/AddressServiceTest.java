package de.cookyapp.service;

import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.service.dto.Address;
import de.cookyapp.service.mocks.AddressRepositoryMock;
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


    @Before
    public void setUp() throws Exception {
        repositoryMock = new AddressRepositoryMock();
        userRepositoryMock = new UserRepositoryMock();
        this.service = new AddressService( repositoryMock, userRepositoryMock );
    }

    @Test
    public void testCreateAddressForUser() throws Exception {

        //initialize User
        UserEntity userDummy = new UserEntity();
        userDummy.setUsername( "CookyTester" );
        userDummy.setId( 1 );

        //save user
        this.userRepositoryMock.save( userDummy );

        //initialize Address
        Address newAddress = new Address();
        newAddress.setStreet( "Teststrasse" );
        newAddress.setPostcode( "7777" );
        newAddress.setHouseNumber( "9" );
        newAddress.setCity( "Testdorf" );


        //act
        this.service.createAddressForUser( userDummy.getId(), newAddress );

        //assert
        Assert.assertEquals( this.userRepositoryMock.findAll().size(), 1 );

    }

    @Test
    public void testRemoveAddressFromUser() throws Exception {

    }

    @Test
    public void updateAddress() throws Exception {

    }

    @Test
    public void getAddress() throws Exception {

    }

    @Test
    public void getAddressForUser() throws Exception {

    }

}