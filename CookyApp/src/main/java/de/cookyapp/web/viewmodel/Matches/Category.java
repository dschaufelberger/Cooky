package de.cookyapp.web.viewmodel.Matches;

/**
 * Created by Jasper on 01.06.2016.
 */
public class Category {

    private String name;
    private String superCategory;

    public Category() {
    }

    public Category( de.cookyapp.service.dto.Category category ) {
        this.name = category.getName();
        this.superCategory = category.getSuperCategory();
    }

    public String getName() {
        return name;
    }

    public void setName( String categoryName ) {
        this.name = categoryName;
    }

    public String getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory( String superCategory ) {
        this.superCategory = superCategory;
    }
}
