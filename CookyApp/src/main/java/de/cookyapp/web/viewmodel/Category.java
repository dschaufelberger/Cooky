package de.cookyapp.web.viewmodel;

import java.util.List;

/**
 * Created by Jasper on 01.06.2016.
 */
public class Category {

    private String categoryName;
    private String superCategory;
    private String [] names;

    public Category () {
    }

    public Category (de.cookyapp.service.dto.Category category) {
        this.categoryName = category.getName();
        this.superCategory = category.getSuperCategory();
    }

    public String getCategoryNames() {
        return categoryName;
    }

    public void setCategoryNames(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(String superCategory) {
        this.superCategory = superCategory;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }
}
