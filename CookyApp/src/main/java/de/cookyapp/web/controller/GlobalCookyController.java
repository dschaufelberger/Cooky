package de.cookyapp.web.controller;

import java.util.LinkedList;
import java.util.List;

import de.cookyapp.enums.SearchType;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.IFriendshipService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.web.viewmodel.friends.FriendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Mario on 24.05.2016.
 */
@ControllerAdvice
public class GlobalCookyController {
    private IFriendshipService friendshipService;
    private IUserCrudService userService;

    @Autowired
    public GlobalCookyController( IFriendshipService friendshipService, IUserCrudService userService ) {
        this.friendshipService = friendshipService;
        this.userService = userService;
    }

    @ModelAttribute
    public void getSearchTypes( Model model ) {
        model.addAttribute( "availableSearchTypes", SearchType.values() );
    }

    @ModelAttribute
    public void getIncomingFriendRequests( Model model ) {
        User user = this.userService.getCurrentUser();
        List<FriendRequest> requests = new LinkedList<>();

        if ( user != null ) {
            List<User> requestingUsers = this.friendshipService.getIncomingFriendRequests( user.getId() );
            for ( User requestingUser : requestingUsers ) {
                FriendRequest request = new FriendRequest( requestingUser );
                request.setRequestedId( user.getId() );
                requests.add( request );
            }
        }

        model.addAttribute( "friendRequests", requests );
    }
}