package de.cookyapp.service.mocks;

import java.util.LinkedList;
import java.util.List;

import de.cookyapp.service.dto.Cookbook;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;

/**
 * Created by Dominik Schaufelberger on 16.05.2016.
 */
public class CookbookManagementServiceMock implements ICookbookManagementService {
    @Override
    public List<Cookbook> getPublicCookbooks() {
        return new LinkedList<>();
    }

    @Override
    public List<Cookbook> getPublicCookbooksForUser( int userId ) {
        return new LinkedList<>();
    }

    @Override
    public List<Cookbook> getCookbooksForUser( int userId ) {
        return new LinkedList<>();
    }

    @Override
    public Cookbook getCookbook( int cookbookId ) {
        return new Cookbook();
    }

    @Override
    public Cookbook getDefaultCookbookForUser( int userId ) {
        return new Cookbook();
    }

    @Override
    public Cookbook createCookbookForUser( int userId, Cookbook cookbook ) {
        return new Cookbook();
    }

    @Override
    public Cookbook createDefaultCookbookForUser( User user ) {
        return new Cookbook();
    }

    @Override
    public void saveCookbook( Cookbook cookbook ) {

    }

    @Override
    public void removeCookbook( int cookbookId ) {

    }
}
