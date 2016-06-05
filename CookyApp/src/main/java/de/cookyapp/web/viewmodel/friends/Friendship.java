package de.cookyapp.web.viewmodel.friends;

import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 05.06.2016.
 */
public class Friendship {
    private String friendname;
    private int friendId;
    private int myId;

    public Friendship( User friend, int myId ) {
        this.friendname = friend.getUsername();
        this.friendId = friend.getId();
        this.myId = myId;
    }

    public String getFriendname() {
        return friendname;
    }

    public int getFriendId() {
        return friendId;
    }

    public int getMyId() {
        return myId;
    }
}
