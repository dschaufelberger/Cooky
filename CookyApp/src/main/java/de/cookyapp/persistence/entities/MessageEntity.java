package de.cookyapp.persistence.entities;

import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.cookyapp.enums.MessageStatus;

/**
 * Created by Dominik on 23.11.2015.
 */
@Entity
@Table( name = "Message", schema = "Cooky_Dev" )
public class MessageEntity {
    private int id;
    private String content;
    private MessageStatus status;
    private int senderId;
    private int receiverId;
    private LocalDateTime sentTime;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID", nullable = false )
    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    @Basic
    @Column( name = "Content", nullable = false, length = 1000 )
    public String getContent() {
        return content;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    @Basic
    @Enumerated( EnumType.STRING )
    @Column( name = "Status", nullable = false, length = 10 )
    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus( MessageStatus status ) {
        this.status = status;
    }

    @Basic
    @Column( name = "SenderID", nullable = false )
    public int getSenderId() {
        return senderId;
    }

    public void setSenderId( int senderId ) {
        this.senderId = senderId;
    }

    @Basic
    @Column( name = "ReceiverID", nullable = false )
    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId( int receiverId ) {
        this.receiverId = receiverId;
    }

    @Basic
    @Column( name = "SentTime", nullable = false )
    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime( LocalDateTime sentTime ) {
        this.sentTime = sentTime;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        MessageEntity that = (MessageEntity) o;

        if ( id != that.id )
            return false;
        if ( senderId != that.senderId )
            return false;
        if ( receiverId != that.receiverId )
            return false;
        if ( content != null ? !content.equals( that.content ) : that.content != null )
            return false;
        if ( status != null ? !status.equals( that.status ) : that.status != null )
            return false;
        if ( sentTime != null ? !sentTime.equals( that.sentTime ) : that.sentTime != null )
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + senderId;
        result = 31 * result + receiverId;
        result = 31 * result + (sentTime != null ? sentTime.hashCode() : 0);
        return result;
    }

    private UserEntity sender;

    @ManyToOne( optional = false )
    public UserEntity getSender() {
        return sender;
    }

    public void setSender( UserEntity sender ) {
        this.sender = sender;
    }

    private UserEntity receiver;

    @ManyToOne( optional = false )
    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver( UserEntity receiver ) {
        this.receiver = receiver;
    }
}
