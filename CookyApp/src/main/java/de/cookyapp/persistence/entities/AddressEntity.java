package de.cookyapp.persistence.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "Address", schema = "Cooky_Dev")
public class AddressEntity {
    private Integer id;
    private String street;
    private String houseNumber;
    private String city;
    private String postcode;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false )
    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "Street", nullable = true, length = 100 )
    public String getStreet() {
        return street;
    }

    public void setStreet( String street ) {
        this.street = street;
    }

    @Basic
    @Column( name = "HouseNumber", nullable = true, length = 10 )
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber( String houseNumber ) {
        this.houseNumber = houseNumber;
    }

    @Basic
    @Column( name = "City", nullable = true, length = 50 )
    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    @Basic
    @Column( name = "Postcode", nullable = true, length = 10 )
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode( String postcode ) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        AddressEntity that = (AddressEntity) o;

        if ( id != null ? !id.equals( that.id ) : that.id != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
