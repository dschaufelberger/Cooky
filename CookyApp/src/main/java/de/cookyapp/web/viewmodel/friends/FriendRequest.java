package de.cookyapp.web.viewmodel.friends;


import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
public class FriendRequest {
    private String inquirerUsername;
    private int inquirerId;
    private int requestedId;

    public FriendRequest( User user ) {
        this.inquirerUsername = user.getUsername();
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
}
