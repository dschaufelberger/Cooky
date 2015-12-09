package de.cookyapp.persistence.entities;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "Address", schema = "Cooky_Dev")
public class AddressEntity {
    private int id;
    private String street;
    private String houseNumber;
    private String city;
    private String postcode;

    @Id
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
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

        if ( street != null ? !street.equals( that.street ) : that.street != null )
            return false;
        if ( houseNumber != null ? !houseNumber.equals( that.houseNumber ) : that.houseNumber != null )
            return false;
        if ( city != null ? !city.equals( that.city ) : that.city != null )
            return false;
        if ( postcode != null ? !postcode.equals( that.postcode ) : that.postcode != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 37;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }
}
