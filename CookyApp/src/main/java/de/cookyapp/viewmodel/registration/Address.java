package de.cookyapp.viewmodel.registration;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import de.cookyapp.persistence.entities.AddressEntity;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Dominik Schaufelberger on 03.12.2015.
 */
public class Address {
    @NotBlank(message = "Bitte geben Sie den Straßennamen an.")
    @Size(min = 1, max = 100, message = "Der Straßenname darf maximal 100 Zeichen lang sein.")
    @Pattern( regexp = "^[a-zA-ZäöüßÄÖÜ](-?[a-zA-ZäöüßÄÖÜß]+)*$", message = "Der Straßenname darf nur aus Klein- und Großbuchstaben sowie einem Bindestrich bestehen.")
    private String street;

    @NotBlank(message = "Bitte geben Sie die Hausnummer an.")
    @Size(min = 1, max = 10, message = "Die Hausnummer darf nicht länger als 10 Zeichen lang sein.")
    @Pattern( regexp = "^[1-9][0-9]*\\u0020?[a-z]?$", message = "Die Hausnummer darf nur aus einer Ziffernfolge gefolgt von einem optionale Kleinbuchstaben bestehen.")    // \\u0020 is the space character
    private String houseNumber;

    @NotBlank(message = "Bitte geben Sie den Wohnort an.")
    @Size(min = 1, max = 50, message = "Der Wohnort darf nicht länger als 50 Zeichen lang sein.")
    @Pattern( regexp = "^[a-zA-ZäöüßÄÖÜ](-?[a-zA-ZäöüßÄÖÜß]+)*$", message = "Der Name des Wohnortes darf nur aus Klein- und Großbuchstaben sowie einem Bindestrich bestehen." )
    private String city;

    @NotBlank(message = "Bitte geben Sie ihre Postleitzahl an.")
    @Size(min = 5, max = 10, message = "Die Postleitzahl muss mindestens aus 5 Ziffern bestehen und darf maximal 10 Zeichen lang sein.")
    @Pattern( regexp = "^[0-9]*$", message = "Die Postleitzahl darf nur aus Ziffern bestehen.")
    private String postcode;

    public Address() {
    }

    public AddressEntity createAddressEntity() {
        AddressEntity address = new AddressEntity();
        address.setStreet( this.street );
        address.setHouseNumber( this.houseNumber );
        address.setCity( this.getCity() );
        address.setPostcode( this.postcode );

        return address;
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
