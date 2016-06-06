package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.service.dto.UserPreference;

/**
 * Created by Jasper on 04.06.2016.
 */
public interface IUserPreferenceCrudService {

    List<UserPreference> getPreferencesByUserId( int id );

    void savePreferences( List<UserPreference> preferences );

    List<UserPreference> getMatches( List<String> categories );

    void deletePreference( UserPreference preference );

    void deletePreference( int id );
}
