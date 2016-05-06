package de.cookyapp.service.exceptions;

/**
 * Created by Dominik Schaufelberger on 02.05.2016.
 */
public class InvalidCookbookId2 extends InvalidId2 {
    public InvalidCookbookId2( int id ) {
        super( id );
    }

    public InvalidCookbookId2( String s, int id ) {
        super( s, id );
    }
}
