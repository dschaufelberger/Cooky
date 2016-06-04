package de.cookyapp.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        List<FriendRequest> requests;

        if ( user != null ) {
            List<User> requestingUsers = this.friendshipService.getIncomingFriendRequests( user.getId() );
            requests = requestingUsers.stream().map( requester -> new FriendRequest( requester ) ).collect( Collectors.toList() );
        } else {
            requests = Collections.EMPTY_LIST;
        }

        model.addAttribute( "friendRequests", requests );
    }
}