package de.cookyapp.persistence.entities;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @Id
    @Column( name = "UserIDOne", nullable = false )
    public int getUserIdOne() {
        return userIdOne;
    }

    public void setUserIdOne( int userIdOne ) {
        this.userIdOne = userIdOne;
    }

    @Id
    @Column( name = "UserIDTwo", nullable = false )
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        FriendshipEntity that = (FriendshipEntity) o;

        if ( userIdOne != that.userIdOne )
            return false;
        if ( userIdTwo != that.userIdTwo )
            return false;
        if ( date != null ? !date.equals( that.date ) : that.date != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userIdOne;
        result = 31 * result + userIdTwo;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    private UserEntity askingFriend;

    @ManyToOne( optional = false )
    public UserEntity getAskingFriend() {
        return askingFriend;
    }

    public void setAskingFriend( UserEntity askingFriend ) {
        this.askingFriend = askingFriend;
    }

    private UserEntity acceptingFriend;

    @ManyToOne( optional = false )
    public UserEntity getAcceptingFriend() {
        return acceptingFriend;
    }

    public void setAcceptingFriend( UserEntity acceptingFriend ) {
        this.acceptingFriend = acceptingFriend;
    }
}
