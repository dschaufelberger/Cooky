package de.cookyapp.service.dto;

import de.cookyapp.persistence.entities.AddressEntity;

/**
 * Created by Dominik Schaufelberger on 09.04.2016.
 */
public class Address {
    private int id;
    private String street;
    private String houseNumber;
    private String city;
    private String postcode;

    public Address() {

    }
    public Address(AddressEntity addressEntity) {
        setId(addressEntity.getId());
        setCity(addressEntity.getCity());
        setHouseNumber(addressEntity.getHouseNumber());
        setPostcode(addressEntity.getPostcode());
        setStreet(addressEntity.getStreet());
    }
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet( String street ) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber( String houseNumber ) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode( String postcode ) {
        this.postcode = postcode;
    }
}
