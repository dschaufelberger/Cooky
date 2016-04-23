package de.cookyapp.service.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dominik Schaufelberger on 21.04.2016.
 */
@Transactional
@Service
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
        User user = this.userCrudService.getUserByID( userId );
        Authentication currentUserAuthentication = this.authentication.getAuthentication();
        List<Cookbook> cookbooks = null;

        if ( user == null ) {
            throw new NotAuthenticated();
        } else if ( !user.getUsername().equals( currentUserAuthentication.getName() )
                && !this.userAuthorization.hasAuthority( currentUserAuthentication, "COOKY_ADMIN" ) ) {
            throw new UserNotAuthorized();
        } else {
            cookbooks = this.cookbookRepository.findByOwnerId( userId ).stream().map( cookbookEntity -> new Cookbook( cookbookEntity ) ).collect( Collectors.toList() );
        }

        return cookbooks != null ? cookbooks : new ArrayList<>();
    }

    @Override
    public Cookbook getCookbook( int cookbookId ) {
        CookbookEntity entitiy = this.cookbookRepository.findOne( cookbookId );
        Cookbook cookbook = null;

        if ( entitiy != null ) {
            if ( this.userCrudService.getCurrentUser().getId() != entitiy.getOwnerId()
                    && !this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" ) ) {
                throw new UserNotAuthorized();
            } else {
                cookbook = new Cookbook( entitiy );
            }
        }

        return cookbook;
    }

    @Override
    public Cookbook createCookbookForUser( int userId, Cookbook cookbook ) {
        User currentUser = this.userCrudService.getCurrentUser();

        if ( currentUser == null ) {
            throw new NotAuthenticated();
        } else if ( currentUser.getId() != userId || !this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" ) ) {
            throw new UserNotAuthorized();
        } else {
            CookbookEntity cookbookEntity = new CookbookEntity();
            cookbookEntity.setName( cookbook.getName() );
            cookbookEntity.setShortDescription( cookbook.getShortDescription() );
            cookbookEntity.setVisibility( cookbook.getVisibility() );
            cookbookEntity.setCreationTime( LocalDateTime.now() );
            cookbookEntity.setDefault( true );
            cookbookEntity.setOwnerId( currentUser.getId() );

            return new Cookbook( this.cookbookRepository.save( cookbookEntity ) );
        }
    }

    @Override
    public void saveCookbook( Cookbook cookbook ) {
        User currentUser = this.userCrudService.getCurrentUser();

        if ( currentUser == null ) {
            throw new NotAuthenticated();
        } else {
            CookbookEntity cookbookEntitiy = this.cookbookRepository.findOne( cookbook.getId() );

            if ( cookbookEntitiy != null ) {
                if ( cookbookEntitiy.getOwnerId() != currentUser.getId() ) {
                    throw new UserNotAuthorized();
                } else if ( !cookbookEntitiy.isDefault() ) {
                    cookbookEntitiy.setName( cookbook.getName() );
                    cookbookEntitiy.setShortDescription( cookbook.getShortDescription() );

                    this.cookbookRepository.save( cookbookEntitiy );
                }
            }
        }
    }

    @Override
    public void removeCookbook( int cookbookId ) {
        User currentUser = this.userCrudService.getCurrentUser();

        if ( currentUser == null ) {
            throw new NotAuthenticated();
        } else {
            CookbookEntity cookbookEntitiy = this.cookbookRepository.findOne( cookbookId );

            if ( cookbookEntitiy != null ) {
                if ( cookbookEntitiy.getOwnerId() != currentUser.getId() ) {
                    throw new UserNotAuthorized();
                } else if ( !cookbookEntitiy.isDefault() ) {
                    this.cookbookRepository.delete( cookbookEntitiy );
                }
            }
        }
    }

    @Override
    public void makeCookbookPublic( int cookbookId ) {
        setCookbookVisibility( cookbookId, CookbookVisibility.PUBLIC );
    }

    @Override
    public void makeCookbookPrivate( int cookbookId ) {
        setCookbookVisibility( cookbookId, CookbookVisibility.PRIVATE );
    }

    @Override
    public void shareCookbookWithFriends( int cookbookId ) {
        setCookbookVisibility( cookbookId, CookbookVisibility.FRIENDS );
    }

    private void setCookbookVisibility( int cookbookId, CookbookVisibility visibility ) {
        User currentUser = this.userCrudService.getCurrentUser();

        if ( currentUser == null ) {
            throw new NotAuthenticated();
        } else {
            CookbookEntity cookbookEntitiy = this.cookbookRepository.findOne( cookbookId );

            if ( cookbookEntitiy != null ) {
                if ( cookbookEntitiy.getOwnerId() != currentUser.getId() ) {
                    throw new UserNotAuthorized();
                } else if ( !cookbookEntitiy.isDefault() && cookbookEntitiy.getVisibility() != visibility ) {
                    cookbookEntitiy.setVisibility( visibility );
                    this.cookbookRepository.save( cookbookEntitiy );
                }
            }
        }
    }
}
