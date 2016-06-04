package de.cookyapp.service.services;

import de.cookyapp.authentication.IAuthenticationFacade;
import de.cookyapp.persistence.entities.UserEntity;
import de.cookyapp.persistence.entities.UserPreferenceEntity;
import de.cookyapp.persistence.repositories.app.ICategoryCrudRepository;
import de.cookyapp.persistence.repositories.app.IUserCrudRepository;
import de.cookyapp.persistence.repositories.app.IUserPreferenceCrudRepository;
import de.cookyapp.service.dto.User;
import de.cookyapp.service.dto.UserPreference;
import de.cookyapp.service.services.interfaces.IUserCrudService;
import de.cookyapp.service.services.interfaces.IUserPreferenceCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public UserPreferenceService (IUserPreferenceCrudRepository preferenceCrudRepository, IUserCrudRepository userCrudRepository, IAuthenticationFacade authentication, ICategoryCrudRepository categoryCrudRepository) {
        this.preferenceCrudRepository = preferenceCrudRepository;
        this.categoryCrudRepository = categoryCrudRepository;
        this.userCrudRepository = userCrudRepository;
        this.authentication = authentication;
    }

    @Override
    public List<UserPreference> getPreferencesByUserId(int id) {
         return mapToUserPreference(preferenceCrudRepository.findByUserId(id));
    }

    @Override
    public void savePreferences(List<UserPreference> preferences) {
        if (preferences != null) {
            for (UserPreference preference : preferences) {
                preferenceCrudRepository.save(mapToEntity(preference));
            }
        }
    }

    @Override
    public void deletePreference(UserPreference preference) {
        if (preference != null) {
            UserPreferenceEntity entity = mapToEntity(preference);
            entity.setId(preference.getId());
            preferenceCrudRepository.delete(entity);
        }
    }

    private UserPreferenceEntity mapToEntity (UserPreference preference) {
        UserPreferenceEntity preferenceEntity = new UserPreferenceEntity();
        preferenceEntity.setUser(userCrudRepository.findOne(preference.getUserId()));
        preferenceEntity.setCategory(categoryCrudRepository.findByName(preference.getCategoryName()));
        return preferenceEntity;
    }

    private List<UserPreference> mapToUserPreference (List<UserPreferenceEntity> entities) {
        List<UserPreference> userPreferences = entities.stream()
                .map(entity -> new UserPreference(entity))
                .collect(Collectors.toList());
        return userPreferences;
    }
}
