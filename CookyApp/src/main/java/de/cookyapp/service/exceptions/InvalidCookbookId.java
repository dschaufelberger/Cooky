package de.cookyapp.service.exceptions;

/**
 * Created by Dominik Schaufelberger on 02.05.2016.
 */
public class InvalidCookbookId extends InvalidId {
    public InvalidCookbookId( int id ) {
        super( id );
    }

    public InvalidCookbookId( String s, int id ) {
        super( s, id );
    }
}
