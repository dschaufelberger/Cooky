package de.cookyapp.service.dto;

import de.cookyapp.persistence.entities.CategoryEntity;

/**
 * Created by Jasper on 01.06.2016.
 */
public class Category {
    private String name;
    private String superCategory;

    public Category() {
    }

    public Category( CategoryEntity categoryEntity ) {
        this.name = categoryEntity.getName();
        this.superCategory = categoryEntity.getSuperCategory();
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory( String superCategory ) {
        this.superCategory = superCategory;
    }
}

