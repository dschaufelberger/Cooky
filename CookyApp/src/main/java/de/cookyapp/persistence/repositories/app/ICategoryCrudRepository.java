package de.cookyapp.persistence.repositories.app;

import de.cookyapp.persistence.entities.CategoryEntity;
import de.cookyapp.service.dto.Category;

import java.util.List;

/**
 * Created by Jasper on 01.06.2016.
 */
public interface ICategoryCrudRepository extends IBaseCrudRepository<CategoryEntity, Integer> {
    CategoryEntity findByName (String name);
}
