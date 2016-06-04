package de.cookyapp.service.dto;

import de.cookyapp.persistence.entities.UserPreferenceEntity;

/**
 * Created by Jasper on 04.06.2016.
 */
public class UserPreference {

    private int id;
    private int userId;
    private String categoryName;

    public UserPreference(){
    }

    public UserPreference(UserPreferenceEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.categoryName = entity.getCategoryName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
