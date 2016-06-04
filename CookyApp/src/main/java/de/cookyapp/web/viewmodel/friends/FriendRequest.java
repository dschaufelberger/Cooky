package de.cookyapp.web.viewmodel.friends;


import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
public class FriendRequest {
    private String requestingUser;

    public FriendRequest( User user ) {
        this.requestingUser = user.getUsername();
    }
}
