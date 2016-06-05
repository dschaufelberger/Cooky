package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
public interface IFriendshipService {
    List<User> getFriends( int forUser );

    List<User> getIncomingFriendRequests( int requestedUser );

    List<User> getOutgoingFriendRequests( int inquiringUser );

    void sendFriendRequest( int from, int to );

    void removeFriend( int user, int friend );

    void acceptFriendRequest( int from, int to );

    void rejectFriendRequest( int from, int to );
}
