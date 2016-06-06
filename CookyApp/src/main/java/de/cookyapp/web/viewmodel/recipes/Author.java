package de.cookyapp.web.viewmodel.recipes;

import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 27.05.2016.
 */
public class Author {
    private int id;
    private String name;

    public Author() {
    }

    public Author( User author ) {
        this.id = author.getId();
        this.name = author.getUsername();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
