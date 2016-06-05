package de.cookyapp.persistence.repositories.app;


import java.util.List;

import de.cookyapp.enums.FriendRequestState;
import de.cookyapp.persistence.entities.FriendshipEntity;
import de.cookyapp.persistence.entities.FriendshipEntityPK;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
public interface IFriendshipRepository extends IBaseCrudRepository<FriendshipEntity, FriendshipEntityPK> {
    List<FriendshipEntity> findByInquiringUser( int inquiringUser );

    List<FriendshipEntity> findByRequestedUser( int requestedUser );

    List<FriendshipEntity> findByRequestedUserAndRequestState( int requestedUser, FriendRequestState requestState );

    List<FriendshipEntity> findByInquiringUserOrRequestedUserAndRequestState( int inquiringUser, int requestedUser, FriendRequestState requestState );

    FriendshipEntity findByInquiringUserAndRequestedUserAndRequestState( int inquiringUser, int requestedUser, FriendRequestState requestState );
}
