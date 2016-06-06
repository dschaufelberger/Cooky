package de.cookyapp.web.viewmodel.search;

/**
 * Created by Dominik Schaufelberger on 06.06.2016.
 */
public class User {
    private int id;
    private String username;

    public User( de.cookyapp.service.dto.User user ) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
