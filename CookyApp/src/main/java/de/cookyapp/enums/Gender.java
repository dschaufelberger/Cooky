package de.cookyapp.enums;

/**
 * Created by Dominik Schaufelberger on 27.11.2015.
 */
public enum Gender {
    MALE( "Male" ), FEMALE( "Female" );

    private String addressForm;

    Gender( String addressForm ) {
        this.addressForm = addressForm;
    }

    public String getAddressForm() {
        return addressForm;
    }
}
