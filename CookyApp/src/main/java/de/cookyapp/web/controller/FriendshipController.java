package de.cookyapp.web.controller;

import java.util.List;

import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.UserNotAuthorized;
import de.cookyapp.service.services.interfaces.IFriendshipService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dominik Schaufelberger on 04.06.2016.
 */
@Controller
@RequestMapping( "/cookys" )
public class FriendshipController {
    private IFriendshipService friendshipService;
    private IUserCrudService userService;

    @Autowired
    public FriendshipController( IFriendshipService friendshipService, IUserCrudService userService ) {
        this.friendshipService = friendshipService;
        this.userService = userService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ModelAndView getCookys() {
        ModelAndView model = new ModelAndView( "test" );

        User currentUser = this.userService.getCurrentUser();
        if ( currentUser != null ) {
            throw new UserNotAuthorized();
        }

        List<User> friends = this.friendshipService.getFriends( currentUser.getId() );
        model.addObject( "friends", friends );

        return model;
    }

    @RequestMapping( value = "/add", method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.OK )
    public void sendFriendRequest() {
    }

    @RequestMapping( value = "/accept", method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.OK )
    public void acceptFriendRequest() {
    }

    @RequestMapping( value = "/reject", method = RequestMethod.POST )
    @ResponseStatus( HttpStatus.OK )
    public void rejectFriendRequest() {
    }
}
