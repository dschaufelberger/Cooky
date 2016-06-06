package de.cookyapp.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.UserNotAuthorized;
import de.cookyapp.service.services.interfaces.IFriendshipService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.friends.Friendship;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
@Controller
@RequestMapping( "/cookys" )
public class FriendshipController {
    private Logger logger = Logger.getLogger( FriendshipController.class );

    private IFriendshipService friendshipService;
    private IUserCrudService userService;

    @Autowired
    public FriendshipController( IFriendshipService friendshipService, IUserCrudService userService ) {
        this.friendshipService = friendshipService;
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView getCookys() {
        ModelAndView model = new ModelAndView( "FriendsTile" );

        User currentUser = this.userService.getCurrentUser();
        if ( currentUser == null ) {
            throw new UserNotAuthorized();
        }

        List<User> friends = this.friendshipService.getFriends( currentUser.getId() );
        List<Friendship> friendships = friends.stream()
                .map( friend -> new Friendship( friend, currentUser.getId() ) )
                .collect( Collectors.toList() );

        List<User> pendingFriends = this.friendshipService.getOutgoingFriendRequests( currentUser.getId() );
        List<Friendship> pendingRequests = pendingFriends.stream()
                .map( friend -> new Friendship( friend, currentUser.getId() ) )
                .collect( Collectors.toList() );

        model.addObject( "friendships", friendships );
        model.addObject( "pendingRequests", pendingRequests );

        return model;
    }

    @RequestMapping( value = "/remove", method = RequestMethod.POST )
    public String removeCookyFriend( @RequestParam( "friend" ) int friend, @RequestParam( "me" ) int user ) {
        this.friendshipService.removeFriend( user, friend );
        return "redirect:/cookys";
    }

    @RequestMapping( value = "/add", method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.OK )
    public void sendFriendRequest() {
    }

    @RequestMapping( value = "/accept", method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.OK )
    public void acceptFriendRequest( @RequestParam( "inquirer" ) int inquirer, @RequestParam( "requested" ) int requested ) {
        this.friendshipService.acceptFriendRequest( inquirer, requested );
    }

    @RequestMapping( value = "/reject", method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.OK )
    public void rejectFriendRequest( @RequestParam( "inquirer" ) int inquirer, @RequestParam( "requested" ) int requested ) {
        this.friendshipService.rejectFriendRequest( inquirer, requested );
    }

    @RequestMapping( value = "/cancel", method = RequestMethod.POST )
    public String cancelFriendRequest( @RequestParam( "inquirer" ) int inquirer, @RequestParam( "requested" ) int requested ) {
        this.friendshipService.cancelFriendRequest( inquirer, requested );
        return "redirect:/cookys";
    }
}
