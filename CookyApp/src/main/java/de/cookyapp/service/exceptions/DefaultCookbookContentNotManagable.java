package de.cookyapp.service.exceptions;

/**
 * Created by Dominik Schaufelberger on 07.05.2016.
 */
public class DefaultCookbookContentNotManagable extends RuntimeException {
    public DefaultCookbookContentNotManagable() {
        super();
    }

    public DefaultCookbookContentNotManagable( String message ) {
        super( message );
    }
}
