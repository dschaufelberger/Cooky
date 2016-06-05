package de.cookyapp.persistence.repositories.app;


import java.util.List;

import de.cookyapp.enums.FriendRequestState;
import de.cookyapp.persistence.entities.FriendshipEntity;
import de.cookyapp.persistence.entities.FriendshipEntityPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
public interface IFriendshipRepository extends IBaseCrudRepository<FriendshipEntity, FriendshipEntityPK> {
    List<FriendshipEntity> findByInquiringUser( int inquiringUser );

    List<FriendshipEntity> findByRequestedUser( int requestedUser );

    List<FriendshipEntity> findByRequestedUserAndRequestState( int requestedUser, FriendRequestState requestState );

    @Query( value = "SELECT fe FROM FriendshipEntity fe WHERE fe.requestState=:requestState AND (fe.inquiringUser=:inquiringUser OR fe.requestedUser=:requestedUser)" )
    List<FriendshipEntity> findByRequestStateAndInquiringUserOrRequestedUser( @Param( "requestState" ) FriendRequestState requestState,
                                                                              @Param( "inquiringUser" ) int inquiringUser,
                                                                              @Param( "requestedUser" ) int requestedUser );

    FriendshipEntity findByInquiringUserAndRequestedUserAndRequestState( int inquiringUser, int requestedUser, FriendRequestState requestState );
}
