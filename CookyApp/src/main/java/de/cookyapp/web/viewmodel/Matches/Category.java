package de.cookyapp.web.viewmodel.Matches;

import java.util.Arrays;

/**
 * Created by Jasper on 01.06.2016.
 */
public class Category {

    private String categoryName;
    private String superCategory;

    public Category () {
    }

    public Category (de.cookyapp.service.dto.Category category) {
        this.categoryName = category.getName();
        this.superCategory = category.getSuperCategory();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(String superCategory) {
        this.superCategory = superCategory;
    }
}
