package de.cookyapp.web.viewmodel.Matches;

import de.cookyapp.service.dto.UserPreference;

/**
 * Created by Jasper on 04.06.2016.
 */
public class UserPreferences {
    private int id;
    private int userId;
    private String categoryName;

    public UserPreferences() {
    }

    public UserPreferences( UserPreference userPreference ) {
        this.id = userPreference.getId();
        this.userId = userPreference.getUserId();
        this.categoryName = userPreference.getCategoryName();
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId ) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName( String categoryName ) {
        this.categoryName = categoryName;
    }
}
