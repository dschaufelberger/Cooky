package de.cookyapp.web.viewmodel.friends;


import de.cookyapp.enums.FriendRequestState;
import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
public class FriendRequest {
    private String inquirerUsername;
    private int inquirerId;
    private int requestedId;
    private FriendRequestState requestState;

    public FriendRequest( User user ) {
        this.inquirerUsername = user.getUsername();
        this.inquirerId = user.getId();
    }

    public int getInquirerId() {
        return inquirerId;
    }

    public int getRequestedId() {
        return requestedId;
    }

    public void setRequestedId( int requestedId ) {
        this.requestedId = requestedId;
    }

    public String getInquirerUsername() {
        return inquirerUsername;
    }

    public FriendRequestState getRequestState() {
        return requestState;
    }
}
