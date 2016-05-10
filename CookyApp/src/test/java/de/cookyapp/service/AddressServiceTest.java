package de.cookyapp.service;

import java.util.LinkedList;

import de.cookyapp.persistence.entities.AddressEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.service.dto.Address;
import de.cookyapp.service.exceptions.InvalidUserId;
import de.cookyapp.service.exceptions.UserNotAuthorized;
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
    private AddressRepositoryMock addressRepositoryMock;
    private UserRepositoryMock userRepositoryMock;
    private AuthenticationMock authentication;
    private IAddressService service;

    @Before
    public void setUp() throws Exception {
        addressRepositoryMock = new AddressRepositoryMock();
        userRepositoryMock = new UserRepositoryMock();
        authentication = new AuthenticationMock( "CookyTester" );
        this.service = new AddressService( addressRepositoryMock, userRepositoryMock, authentication );
    }

    @Test
    public void createAddressForExisistingUser() throws Exception {
        //arrange
        UserEntity dummy = new UserEntity();
        dummy.setId( 5 );
        dummy.setUsername( "CookyTester" );
        LinkedList<UserEntity> users = new LinkedList<>();
        users.add( dummy );
        this.userRepositoryMock.setUsers( users );

        Address address = new Address();
        address.setCity( "Karlsruhe" );
        address.setPostcode( "12345" );
        address.setHouseNumber( "42" );
        address.setStreet( "Erzbergerstraße" );

        //act
        this.service.createAddressForUser( dummy.getId(), address );

        //assert
        Integer id;
        Assert.assertEquals( 1, this.addressRepositoryMock.getEntities().size() );
        Assert.assertNotEquals( 0, (id = this.addressRepositoryMock.getEntities().get( 0 ).getId()) != null ? id.intValue() : 0 );
    }

    @Test
    public void createAddressForNonexistantUser() throws Exception {
        thrown.expect( InvalidUserId.class );

        //arrange
        Address address = new Address();
        address.setCity( "Karlsruhe" );
        address.setPostcode( "12345" );
        address.setHouseNumber( "42" );
        address.setStreet( "Erzbergerstraße" );

        //act
        this.service.createAddressForUser( 404, address );

        //assert
    }

    @Test
    public void removeAddressFromUser() throws Exception {
        //arrange
        AddressEntity address = new AddressEntity();
        address.setId( 42 );
        address.setCity( "Karlsruhe" );
        address.setPostcode( "12345" );
        address.setHouseNumber( "1" );
        address.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( address );
        this.addressRepositoryMock.setEntities( addresses );

        UserEntity dummy = new UserEntity();
        dummy.setId( 2 );
        dummy.setUsername( "CookyTester" );
        dummy.setAddress( address );
        LinkedList<UserEntity> users = new LinkedList<>();
        users.add( dummy );
        this.userRepositoryMock.setUsers( users );

        //act
        this.service.removeAddressFromUser( dummy.getId(), address.getId() );

        //assert
        Assert.assertEquals( 0, this.addressRepositoryMock.getEntities().size() );
        Assert.assertNull( this.userRepositoryMock.getUsers().getFirst().getAddress() );
    }

    @Test
    public void updateExistingAddressForAuthorizedUser() throws Exception {
        //arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId( 42 );
        addressEntity.setCity( "Karlsruhe" );
        addressEntity.setPostcode( "12345" );
        addressEntity.setHouseNumber( "1" );
        addressEntity.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( addressEntity );
        this.addressRepositoryMock.setEntities( addresses );

        UserEntity dummy = new UserEntity();
        dummy.setId( 2 );
        dummy.setUsername( "CookyTester" );
        dummy.setAddress( addressEntity );
        LinkedList<UserEntity> users = new LinkedList<>();
        users.add( dummy );
        this.userRepositoryMock.setUsers( users );

        //act
        Address address = new Address( addressEntity );
        address.setStreet( "Other street" );
        address.setCity( "Other city" );
        address.setHouseNumber( "42" );
        address.setPostcode( "54321" );
        this.service.updateAddress( address );

        //assert
        AddressEntity assertionAddress = this.addressRepositoryMock.getEntities().get( 0 );
        Assert.assertNotNull( assertionAddress );
        Assert.assertEquals( "Other street", assertionAddress.getStreet() );
        Assert.assertEquals( "Other city", assertionAddress.getCity() );
        Assert.assertEquals( "42", assertionAddress.getHouseNumber() );
        Assert.assertEquals( "54321", assertionAddress.getPostcode() );
    }

    @Test
    public void updateNonExistantAddress() throws Exception {
        //arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId( 42 );
        addressEntity.setCity( "Karlsruhe" );
        addressEntity.setPostcode( "12345" );
        addressEntity.setHouseNumber( "1" );
        addressEntity.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( addressEntity );
        this.addressRepositoryMock.setEntities( addresses );

        UserEntity dummy = new UserEntity();
        dummy.setId( 2 );
        dummy.setUsername( "CookyTester" );
        dummy.setAddress( addressEntity );
        LinkedList<UserEntity> users = new LinkedList<>();
        users.add( dummy );
        this.userRepositoryMock.setUsers( users );

        //act
        Address address = new Address( addressEntity );
        address.setId( 0 );
        address.setStreet( "Other street" );
        address.setCity( "Other city" );
        address.setHouseNumber( "42" );
        address.setPostcode( "54321" );
        this.service.updateAddress( address );

        //assert
        AddressEntity assertionAddress = this.addressRepositoryMock.getEntities().get( 0 );
        Assert.assertNotNull( assertionAddress );
        Assert.assertEquals( "Erzbergerstraße", assertionAddress.getStreet() );
        Assert.assertEquals( "Karlsruhe", assertionAddress.getCity() );
        Assert.assertEquals( "1", assertionAddress.getHouseNumber() );
        Assert.assertEquals( "12345", assertionAddress.getPostcode() );
    }

    @Test
    public void updateExistingAddressForUnauthorizedUser() throws Exception {
        thrown.expect( UserNotAuthorized.class );

        //arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId( 42 );
        addressEntity.setCity( "Karlsruhe" );
        addressEntity.setPostcode( "12345" );
        addressEntity.setHouseNumber( "1" );
        addressEntity.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( addressEntity );
        this.addressRepositoryMock.setEntities( addresses );

        UserEntity dummy = new UserEntity();
        dummy.setId( 2 );
        dummy.setUsername( "NotCookyTester" );
        dummy.setAddress( addressEntity );
        LinkedList<UserEntity> users = new LinkedList<>();
        users.add( dummy );
        this.userRepositoryMock.setUsers( users );

        //act
        Address address = new Address( addressEntity );
        address.setStreet( "Other street" );
        address.setCity( "Other city" );
        address.setHouseNumber( "42" );
        address.setPostcode( "54321" );
        this.service.updateAddress( address );

        //assert
    }

    @Test
    public void getAddressForExistingAndAuthorizedUser() throws Exception {
        //arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId( 42 );
        addressEntity.setCity( "Karlsruhe" );
        addressEntity.setPostcode( "12345" );
        addressEntity.setHouseNumber( "1" );
        addressEntity.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( addressEntity );
        this.addressRepositoryMock.setEntities( addresses );

        UserEntity dummy = new UserEntity();
        dummy.setId( 2 );
        dummy.setUsername( "CookyTester" );
        dummy.setAddress( addressEntity );
        LinkedList<UserEntity> users = new LinkedList<>();
        users.add( dummy );
        this.userRepositoryMock.setUsers( users );

        //act
        Address address = this.service.getAddressForUser( dummy.getId() );

        Assert.assertNotNull( address );
        Assert.assertEquals( "Erzbergerstraße", address.getStreet() );
        Assert.assertEquals( "Karlsruhe", address.getCity() );
        Assert.assertEquals( "1", address.getHouseNumber() );
        Assert.assertEquals( "12345", address.getPostcode() );
    }

    @Test
    public void getAddressForExistingButUnauthorizedUser() throws Exception {
        //arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId( 42 );
        addressEntity.setCity( "Karlsruhe" );
        addressEntity.setPostcode( "12345" );
        addressEntity.setHouseNumber( "1" );
        addressEntity.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( addressEntity );
        this.addressRepositoryMock.setEntities( addresses );

        UserEntity dummy = new UserEntity();
        dummy.setId( 2 );
        dummy.setUsername( "NotCookyTester" );
        dummy.setAddress( addressEntity );
        LinkedList<UserEntity> users = new LinkedList<>();
        users.add( dummy );
        this.userRepositoryMock.setUsers( users );

        //act
        Address address = this.service.getAddressForUser( dummy.getId() );

        //assert
        Assert.assertNull( address );
    }

    @Test
    public void getAddressForNonexistingUser() throws Exception {
        thrown.expect( InvalidUserId.class );

        //arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId( 42 );
        addressEntity.setCity( "Karlsruhe" );
        addressEntity.setPostcode( "12345" );
        addressEntity.setHouseNumber( "1" );
        addressEntity.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( addressEntity );
        this.addressRepositoryMock.setEntities( addresses );

        UserEntity dummy = new UserEntity();
        dummy.setId( 2 );
        dummy.setUsername( "NotCookyTester" );
        dummy.setAddress( addressEntity );
        LinkedList<UserEntity> users = new LinkedList<>();
        users.add( dummy );
        this.userRepositoryMock.setUsers( users );

        //act
        Address address = this.service.getAddressForUser( 42 );

        //assert
    }

    @Test
    public void getExistingAddress() throws Exception {
        //arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId( 42 );
        addressEntity.setCity( "Karlsruhe" );
        addressEntity.setPostcode( "12345" );
        addressEntity.setHouseNumber( "1" );
        addressEntity.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( addressEntity );
        this.addressRepositoryMock.setEntities( addresses );

        //act
        Address address = this.service.getAddress( 42 );

        //assert
        Assert.assertNotNull( address );
        Assert.assertEquals( "Erzbergerstraße", address.getStreet() );
        Assert.assertEquals( "Karlsruhe", address.getCity() );
        Assert.assertEquals( "1", address.getHouseNumber() );
        Assert.assertEquals( "12345", address.getPostcode() );
    }

    @Test
    public void getNonexistantAddress() throws Exception {
        //arrange
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId( 42 );
        addressEntity.setCity( "Karlsruhe" );
        addressEntity.setPostcode( "12345" );
        addressEntity.setHouseNumber( "1" );
        addressEntity.setStreet( "Erzbergerstraße" );
        LinkedList<AddressEntity> addresses = new LinkedList<>();
        addresses.add( addressEntity );
        this.addressRepositoryMock.setEntities( addresses );

        //act
        Address address = this.service.getAddress( 1 );

        //assert
        Assert.assertNull( address );
    }
}