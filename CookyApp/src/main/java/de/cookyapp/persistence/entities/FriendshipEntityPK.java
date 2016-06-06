package de.cookyapp.persistence.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by Dominik on 23.11.2015.
 */
public class FriendshipEntityPK implements Serializable {
    private int inquiringUser;
    private int requestedUser;

    @Column( name = "InquiringUser", nullable = false, insertable = false, updatable = false )
    @Id
    public int getInquiringUser() {
        return inquiringUser;
    }

    public void setInquiringUser( int inquiringUser ) {
        this.inquiringUser = inquiringUser;
    }

    @Column( name = "RequestedUser", nullable = false, insertable = false, updatable = false )
    @Id
    public int getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser( int requestedUser ) {
        this.requestedUser = requestedUser;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        FriendshipEntityPK that = (FriendshipEntityPK) o;

        if ( inquiringUser != that.inquiringUser )
            return false;
        return requestedUser == that.requestedUser;

    }

    @Override
    public int hashCode() {
        int result = inquiringUser;
        result = 31 * result + requestedUser;
        return result;
    }
}
