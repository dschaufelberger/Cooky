package de.cookyapp.service.exceptions;

/**
 * Created by Dominik Schaufelberger on 07.05.2016.
 */
public class DefaultCookbookContenNotManagable extends RuntimeException {
    public DefaultCookbookContenNotManagable() {
        super();
    }

    public DefaultCookbookContenNotManagable( String message ) {
        super( message );
    }
}
