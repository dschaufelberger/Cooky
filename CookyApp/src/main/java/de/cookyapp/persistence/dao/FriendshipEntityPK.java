package de.cookyapp.persistence.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by Dominik on 23.11.2015.
 */
public class FriendshipEntityPK implements Serializable {
    private int userIdOne;
    private int userIdTwo;

    @Column( name = "UserIDOne", nullable = false )
    @Id
    public int getUserIdOne() {
        return userIdOne;
    }

    public void setUserIdOne( int userIdOne ) {
        this.userIdOne = userIdOne;
    }

    @Column( name = "UserIDTwo", nullable = false )
    @Id
    public int getUserIdTwo() {
        return userIdTwo;
    }

    public void setUserIdTwo( int userIdTwo ) {
        this.userIdTwo = userIdTwo;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        FriendshipEntityPK that = (FriendshipEntityPK) o;

        if ( userIdOne != that.userIdOne )
            return false;
        if ( userIdTwo != that.userIdTwo )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userIdOne;
        result = 31 * result + userIdTwo;
        return result;
    }
}
