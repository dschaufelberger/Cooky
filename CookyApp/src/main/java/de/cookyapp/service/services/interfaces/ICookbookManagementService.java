package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.service.dto.Cookbook;
import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 21.04.2016.
 */
public interface ICookbookManagementService {
    List<Cookbook> getPublicCookbooks();

    List<Cookbook> getPublicCookbooksForUser( int userId );

    List<Cookbook> getCookbooksForUser( int userId );

    Cookbook getCookbook( int cookbookId );

    Cookbook getDefaultCookbookForUser( int userId );

    Cookbook createCookbookForUser( int userId, Cookbook cookbook );

    Cookbook createDefaultCookbookForUser( User user );

    void saveCookbook( Cookbook cookbook );

    void removeCookbook( int cookbookId );
}
