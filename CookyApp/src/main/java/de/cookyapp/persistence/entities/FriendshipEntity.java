package de.cookyapp.persistence.entities;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.cookyapp.enums.FriendRequestState;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "Friendship", schema = "Cooky_Dev" )
@IdClass( FriendshipEntityPK.class )
public class FriendshipEntity {
    private int userIdOne;
    private int userIdTwo;
    private LocalDateTime date;
    private FriendRequestState requestState;

    private UserEntity inquiringUser;
    private UserEntity requestedUser;

    @Id
    @Column( name = "InquiringUser", nullable = false )
    public int getUserIdOne() {
        return userIdOne;
    }

    public void setUserIdOne( int userIdOne ) {
        this.userIdOne = userIdOne;
    }

    @Id
    @Column( name = "RequestedUser", nullable = false )
    public int getUserIdTwo() {
        return userIdTwo;
    }

    public void setUserIdTwo( int userIdTwo ) {
        this.userIdTwo = userIdTwo;
    }

    @Basic
    @Column( name = "Date", nullable = true )
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate( LocalDateTime date ) {
        this.date = date;
    }

    @Basic
    @Enumerated( EnumType.STRING )
    @Column( name = "RequestState", nullable = false, length = 10 )
    public FriendRequestState getRequestState() {
        return requestState;
    }

    public void setRequestState( FriendRequestState requestState ) {
        this.requestState = requestState;
    }

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false )
    @JoinColumn( name = "InquiringUser" )
    public UserEntity getInquiringUser() {
        return inquiringUser;
    }

    public void setInquiringUser( UserEntity askingFriend ) {
        this.inquiringUser = askingFriend;
    }

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false )
    @JoinColumn( name = "RequestedUser" )
    public UserEntity getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser( UserEntity acceptingFriend ) {
        this.requestedUser = acceptingFriend;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        FriendshipEntity that = (FriendshipEntity) o;

        if ( userIdOne != that.userIdOne )
            return false;
        return userIdTwo == that.userIdTwo;

    }

    @Override
    public int hashCode() {
        int result = userIdOne;
        result = 31 * result + userIdTwo;
        return result;
    }
}
