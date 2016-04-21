package de.cookyapp.service.services;

import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.authentication.IUserAuthorization;
import de.cookyapp.enums.CookbookVisibility;
import de.cookyapp.persistence.entities.CookbookEntity;
import de.cookyapp.persistence.repositories.ICookbookRepository;
import de.cookyapp.service.dto.Cookbook;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.NotAuthenticated;
import de.cookyapp.service.exceptions.UserNotAuthorized;
import de.cookyapp.service.services.interfaces.ICookbookManagementService;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Dominik Schaufelberger on 21.04.2016.
 */
public class CookbookManagementService implements ICookbookManagementService {
    private ICookbookRepository cookbookRepository;
    private IUserCrudService userCrudService;
    private IUserAuthorization userAuthorization;
    private IAuthenticationFacade authentication;

    @Autowired
    public CookbookManagementService( ICookbookRepository cookbookRepository, IUserCrudService userCrudService, IUserAuthorization userAuthorization, IAuthenticationFacade authentication ) {
        this.cookbookRepository = cookbookRepository;
        this.userCrudService = userCrudService;
        this.userAuthorization = userAuthorization;
        this.authentication = authentication;
    }

    @Override
    public List<Cookbook> getPublicCookbooks() {
        return this.cookbookRepository.findByVisibility( CookbookVisibility.PUBLIC )
                .stream()
                .map( cookbookEntity -> new Cookbook( cookbookEntity ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<Cookbook> getPublicCookbooksFromUser( int userId ) {
        return this.cookbookRepository.findByOwnerIdAndVisibility( userId, CookbookVisibility.PUBLIC )
                .stream()
                .map( cookbookEntity -> new Cookbook( cookbookEntity ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<Cookbook> getCookbooksForUser( int userId ) {
        User user = this.userCrudService.getCurrentUser();

        if ( user == null ) {
            throw new NotAuthenticated();
        } else {
            if ( user.getId() != userId && !this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" ) ) {
                throw new UserNotAuthorized();
            } else {
                return this.cookbookRepository.findByOwnerId( userId ).stream().map( cookbookEntity -> new Cookbook( cookbookEntity ) ).collect( Collectors.toList() );
            }
        }
    }

    @Override
    public Cookbook getCookbook( int cookbookId ) {
        CookbookEntity entitiy = this.cookbookRepository.findOne( cookbookId );
        Cookbook cookbook = null;

        if ( entitiy != null
                && (this.userCrudService.getCurrentUser().getId() == entitiy.getOwnerId()
                || !this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" )) ) {
            cookbook = new Cookbook( entitiy );
        }

        return cookbook;
    }

    @Override
    public void createCookbookForUser( int userId, Cookbook cookbook ) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void saveCookbook( Cookbook cookbook ) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void removeCookbook( int cookbookId ) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void removeCookbook( Cookbook cookbook ) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void makeCookbookPublic( int cookbookId ) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void makeCookbookPrivate( int cookbookId ) {

        throw new UnsupportedOperationException();
    }

    @Override
    public void shareCookbookWithFriends( int cookbookId ) {

        throw new UnsupportedOperationException();
    }
}
