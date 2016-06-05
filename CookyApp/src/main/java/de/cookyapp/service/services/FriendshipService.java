package de.cookyapp.service.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.enums.FriendRequestState;
import de.cookyapp.persistence.entities.FriendshipEntity;
import de.cookyapp.persistence.entities.FriendshipEntityPK;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.app.IFriendshipRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.InvalidUserId;
import de.cookyapp.service.services.interfaces.IFriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
@Service
@Transactional
public class FriendshipService implements IFriendshipService {
    private IFriendshipRepository friendshipRepository;
    private IUserCrudRepository userRepository;

    @Autowired
    public FriendshipService( IFriendshipRepository friendshipRepository, IUserCrudRepository userRepository ) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getFriends( int forUser ) {
        List<FriendshipEntity> requests = this.friendshipRepository.findByRequestedUserOrInquiringUserAndRequestState( forUser, forUser, FriendRequestState.ACCEPTED );
        List<User> friends = new ArrayList<>( requests.size() );

        for ( FriendshipEntity request : requests ) {
            if ( request.getInquirer().getId() == forUser ) {
                friends.add( new User( request.getRequester() ) );
            } else if ( request.getRequester().getId() == forUser ) {
                friends.add( new User( request.getInquirer() ) );
            }
        }

        return friends;
    }

    @Override
    public List<User> getIncomingFriendRequests( int requestedUser ) {
        List<FriendshipEntity> requests = this.friendshipRepository.findByRequestedUserAndRequestState( requestedUser, FriendRequestState.PENDING );

        return requests.stream()
                .map( request -> new User( request.getInquirer() ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<User> getOutgoingFriendRequests( int inquiringUser ) {
        List<FriendshipEntity> requests = this.friendshipRepository.findByInquiringUser( inquiringUser );

        List<FriendshipEntity> rejected = new LinkedList<>();
        List<FriendshipEntity> pending = new LinkedList<>();
        for ( FriendshipEntity request : requests ) {
            if ( request.getRequestState() == FriendRequestState.PENDING ) {
                pending.add( request );
            } else if ( request.getRequestState() == FriendRequestState.REJECTED ) {
                rejected.add( request );
            }
        }

        this.friendshipRepository.delete( rejected );

        return pending.stream().map( request -> new User( request.getRequester() ) ).collect( Collectors.toList() );
    }

    @Override
    public void sendFriendRequest( int from, int to ) {
        UserEntity inquiring = getUser( from );
        UserEntity requested = getUser( to );

        FriendshipEntityPK id = new FriendshipEntityPK();
        id.setInquiringUser( inquiring.getId() );
        id.setRequestedUser( requested.getId() );

        if ( this.friendshipRepository.findOne( id ) == null ) {
            FriendshipEntity friendship = new FriendshipEntity();
            friendship.setInquirer( inquiring );
            friendship.setRequester( requested );
            friendship.setDate( LocalDateTime.now() );
            friendship.setRequestState( FriendRequestState.PENDING );
        }
    }

    @Override
    public void acceptFriendRequest( int from, int to ) {
        FriendshipEntity request = this.friendshipRepository.findByRequestedUserAndInquiringUserAndRequestState( from, to, FriendRequestState.PENDING );
        if ( request != null ) {
            request.setRequestState( FriendRequestState.ACCEPTED );
            request.setDate( LocalDateTime.now() );
            this.friendshipRepository.save( request );
        }
    }

    @Override
    public void rejectFriendRequest( int from, int to ) {
        FriendshipEntity request = this.friendshipRepository.findByRequestedUserAndInquiringUserAndRequestState( from, to, FriendRequestState.PENDING );
        if ( request != null ) {
            request.setRequestState( FriendRequestState.REJECTED );
            request.setDate( LocalDateTime.now() );
            this.friendshipRepository.save( request );
        }
    }

    private UserEntity getUser( int id ) {
        UserEntity user = this.userRepository.findOne( id );
        if ( user == null ) {
            throw new InvalidUserId( id );
        }
        return user;
    }
}
