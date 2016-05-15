package de.cookyapp.service.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.authentication.IUserAuthorization;
import de.cookyapp.enums.CookbookVisibility;
import de.cookyapp.persistence.entities.CookbookEntity;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.repositories.app.ICookbookRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.service.dto.Cookbook;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.exceptions.InvalidCookbookId;
import de.cookyapp.service.exceptions.InvalidUserId;
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
    private IUserCrudRepository userCrudRepository;
    private IUserCrudService userCrudService;
    private IUserAuthorization userAuthorization;
    private IAuthenticationFacade authentication;

    @Autowired
    public CookbookManagementService( ICookbookRepository cookbookRepository, IUserCrudRepository userCrudRepository, IUserCrudService userCrudService, IUserAuthorization userAuthorization, IAuthenticationFacade authentication ) {
        this.cookbookRepository = cookbookRepository;
        this.userCrudRepository = userCrudRepository;
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
    public List<Cookbook> getPublicCookbooksForUser( int userId ) {
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
            /*
             *  TODO [dodo] in addition to the owners cookbooks, we need to load the shared cookbooks of friends aswell.
             *  this needs to be implemented when the friend management is done.
             */
            cookbooks = this.cookbookRepository.findByOwnerId( userId )
                    .stream()
                    .filter( cookbookEntity -> !cookbookEntity.getIsDefault() )
                    .map( cookbookEntity -> new Cookbook( cookbookEntity ) )
                    .collect( Collectors.toList() );
        }

        return cookbooks != null ? cookbooks : new ArrayList<>();
    }

    @Override
    public Cookbook getCookbook( int cookbookId ) {
        CookbookEntity entitiy = this.cookbookRepository.findOne( cookbookId );
        Cookbook cookbook = null;

        if ( entitiy != null ) {
            if ( !this.authentication.getAuthentication().getName().equals( entitiy.getOwner().getUsername() )
                    && !this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" ) ) {
                throw new InvalidCookbookId( "Cookbook with given id does not exist.", cookbookId );
            } else if ( !entitiy.getIsDefault() ) {
                cookbook = getCookbookIfExistant( entitiy );
            }
        } else {
            throw new InvalidCookbookId( cookbookId );
        }

        return cookbook;
    }

    @Override
    public Cookbook getDefaultCookbookForUser( int userId ) {
        UserEntity user = this.userCrudRepository.findOne( userId );

        if ( user == null ) {
            throw new InvalidUserId( userId );
        } else if ( !this.authentication.getAuthentication().getName().equals( user.getUsername() )
                && !this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" ) ) {
            throw new UserNotAuthorized();
        } else {
            CookbookEntity defaultCookbook = this.cookbookRepository.findByOwnerIdAndIsDefaultTrue( userId );
            return getCookbookIfExistant( defaultCookbook );
        }
    }

    @Override
    public Cookbook createCookbookForUser( int userId, Cookbook cookbook ) {
        UserEntity user = this.userCrudRepository.findOne( userId );

        if ( user == null ) {
            throw new InvalidUserId( userId );
        } else if ( !this.authentication.getAuthentication().getName().equals( user.getUsername() )
                && !this.userAuthorization.hasAuthority( this.authentication.getAuthentication(), "COOKY_ADMIN" ) ) {
            throw new UserNotAuthorized();
        } else {
            return createCookbook( user, cookbook, false );
        }
    }

    @Override
    public Cookbook createDefaultCookbookForUser( int userId, Cookbook cookbook ) {
        UserEntity user = this.userCrudRepository.findOne( userId );

        if ( user == null ) {
            throw new InvalidUserId( userId );
        } else {
            return createCookbook( user, cookbook, true );
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
                } else if ( !cookbookEntitiy.getIsDefault() ) {
                    cookbookEntitiy.setName( cookbook.getName() );
                    cookbookEntitiy.setShortDescription( cookbook.getShortDescription() );
                    cookbookEntitiy.setVisibility( cookbook.getVisibility() );

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
                } else if ( !cookbookEntitiy.getIsDefault() ) {
                    this.cookbookRepository.delete( cookbookEntitiy );
                }
            }
        }
    }

    private Cookbook getCookbookIfExistant( CookbookEntity cookbookEntity ) {
        if ( cookbookEntity == null ) {
            return null;
        }

        return new Cookbook( cookbookEntity );
    }

    private Cookbook createCookbook( UserEntity user, Cookbook cookbook, boolean isDefault ) {
        CookbookEntity cookbookEntity = new CookbookEntity();
        cookbookEntity.setName( cookbook.getName() );
        cookbookEntity.setShortDescription( cookbook.getShortDescription() );
        cookbookEntity.setVisibility( cookbook.getVisibility() );
        cookbookEntity.setCreationTime( LocalDateTime.now() );
        cookbookEntity.setIsDefault( isDefault );
        cookbookEntity.setOwner( user );

        CookbookEntity entity = this.cookbookRepository.save( cookbookEntity );

        return new Cookbook( entity );
    }
}
