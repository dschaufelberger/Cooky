package de.cookyapp.service.services.interfaces;

import de.cookyapp.enums.Role;
import de.cookyapp.service.dto.User;

/**
 * Created by Dominik Schaufelberger on 14.05.2016.
 */
public interface IUserRoleService {
    void getAllRoles();

    void addRoleToUser( User user, Role role );

    void removeRoleFromUser( User user, Role role );
}
