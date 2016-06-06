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
    private int inquiringUser;
    private int requestedUser;
    private LocalDateTime date;
    private FriendRequestState requestState;

    private UserEntity inquirer;
    private UserEntity requester;

    @Id
    @Column( name = "InquiringUser", nullable = false, insertable = false, updatable = false )
    public int getInquiringUser() {
        return inquiringUser;
    }

    public void setInquiringUser( int userIdOne ) {
        this.inquiringUser = userIdOne;
    }

    @Id
    @Column( name = "RequestedUser", nullable = false, insertable = false, updatable = false )
    public int getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser( int userIdTwo ) {
        this.requestedUser = userIdTwo;
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
    public UserEntity getInquirer() {
        return inquirer;
    }

    public void setInquirer( UserEntity askingFriend ) {
        this.inquirer = askingFriend;
    }

    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false )
    @JoinColumn( name = "RequestedUser" )    //, insertable = false, updatable = false
    public UserEntity getRequester() {
        return requester;
    }

    public void setRequester( UserEntity acceptingFriend ) {
        this.requester = acceptingFriend;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        FriendshipEntity that = (FriendshipEntity) o;

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
