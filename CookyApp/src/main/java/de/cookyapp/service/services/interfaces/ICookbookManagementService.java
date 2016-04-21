package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.service.dto.Cookbook;

/**
 * Created by Dominik Schaufelberger on 21.04.2016.
 */
public interface ICookbookManagementService {
    List<Cookbook> getPublicCookbooks();

    List<Cookbook> getPublicCookbooksFromUser( int userId );

    List<Cookbook> getCookbooksForUser( int userId );

    Cookbook getCookbook( int cookbookId );

    void createCookbookForUser( int userId, Cookbook cookbook );

    void saveCookbook( Cookbook cookbook );

    void removeCookbook( int cookbookId );

    void removeCookbook( Cookbook cookbook );

    void makeCookbookPublic( int cookbookId );

    void makeCookbookPrivate( int cookbookId );

    void shareCookbookWithFriends( int cookbookId );
}
