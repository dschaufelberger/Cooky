package de.cookyapp.service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.UserPreferenceEntity;
import de.cookyapp.persistence.repositories.app.ICategoryCrudRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.persistence.repositories.app.IUserPreferenceCrudRepository;
import de.cookyapp.service.dto.UserPreference;
import de.cookyapp.service.services.interfaces.IUserPreferenceCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jasper on 04.06.2016.
 */
@Transactional
@Service
public class UserPreferenceService implements IUserPreferenceCrudService {

    private IUserPreferenceCrudRepository preferenceCrudRepository;
    private ICategoryCrudRepository categoryCrudRepository;
    private IUserCrudRepository userCrudRepository;
    private IAuthenticationFacade authentication;

    @Autowired
    public UserPreferenceService( IUserPreferenceCrudRepository preferenceCrudRepository, IUserCrudRepository userCrudRepository, IAuthenticationFacade authentication, ICategoryCrudRepository categoryCrudRepository ) {
        this.preferenceCrudRepository = preferenceCrudRepository;
        this.categoryCrudRepository = categoryCrudRepository;
        this.userCrudRepository = userCrudRepository;
        this.authentication = authentication;
    }

    @Override
    public List<UserPreference> getPreferencesByUserId( int id ) {
        return mapToUserPreference( preferenceCrudRepository.findByUserId( id ) );
    }

    @Override
    public void savePreferences( List<UserPreference> preferences ) {
        if ( preferences != null && preferences.size() > 0 ) {
            List<UserPreferenceEntity> result = new ArrayList<>();
            for ( UserPreference preference : preferences ) {
                if ( preferenceCrudRepository.findByCategoryNameAndUserId( preference.getCategoryName(), preference.getUserId() ).size() == 0 ) {
                    UserPreferenceEntity entity = mapToEntity( preference );
                    result.add( entity );
                }
            }
            preferenceCrudRepository.save( result );
        }
    }

    @Override
    public void deletePreference( UserPreference preference ) {
        if ( preference != null ) {
            UserPreferenceEntity entity = mapToEntity( preference );
            entity.setId( preference.getId() );
            preferenceCrudRepository.delete( entity );
        }
    }

    @Override
    public void deletePreference( int id ) {
        UserPreferenceEntity preference = preferenceCrudRepository.findOne( id );
        if ( preference != null ) {
            preferenceCrudRepository.delete( preference );
        }
    }

    @Override
    public List<UserPreference> getMatches( List<String> categories ) {
        List<UserPreference> preferences = new ArrayList<>();
        if ( categories != null ) {
            preferences = mapToUserPreferenceFilter( preferenceCrudRepository.findByCategoryNameIn( categories ) );
        }
        return preferences;
    }

    private UserPreferenceEntity mapToEntity( UserPreference preference ) {
        UserPreferenceEntity preferenceEntity = new UserPreferenceEntity();
        preferenceEntity.setUserId( preference.getUserId() );
        preferenceEntity.setCategoryName( preference.getCategoryName() );
        return preferenceEntity;
    }

    private List<UserPreference> mapToUserPreferenceFilter( List<UserPreferenceEntity> entities ) {
        List<UserPreference> userPreferences = entities.stream()
                .filter( entity -> entity.getUserId() != userCrudRepository.findByUsername( authentication.getAuthentication().getName() ).getId() )
                .map( entity -> new UserPreference( entity ) )
                .distinct()
                .collect( Collectors.toList() );
        return userPreferences;
    }

    private List<UserPreference> mapToUserPreference( List<UserPreferenceEntity> entities ) {
        List<UserPreference> userPreferences = entities.stream()
                .map( entity -> new UserPreference( entity ) )
                .distinct()
                .collect( Collectors.toList() );
        return userPreferences;
    }
}
