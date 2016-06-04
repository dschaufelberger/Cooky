package de.cookyapp.web.viewmodel.Matches;

import java.util.List;

/**
 * Created by Jasper on 04.06.2016.
 */
public class CategoryList {

    private List<Category> categories;
    private String[] userCategories;

    public CategoryList() {
    }

    public CategoryList (List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String[] getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(String[] userCategories) {
        this.userCategories = userCategories;
    }
}
