package de.cookyapp.service.services;

import de.cookyapp.persistence.entities.CategoryEntity;
import de.cookyapp.persistence.repositories.app.ICategoryCrudRepository;
import de.cookyapp.service.dto.Category;
import de.cookyapp.service.services.interfaces.ICategoryCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasper on 01.06.2016.
 */
@Transactional
@Service
public class CategoryCrudService implements ICategoryCrudService {
    private ICategoryCrudRepository categoryCrudRepository;

    @Autowired
    public CategoryCrudService (ICategoryCrudRepository categoryCrudRepository) {
        this.categoryCrudRepository = categoryCrudRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return mapToCategory(categoryCrudRepository.findAll());
    }


    private List<Category> mapToCategory (List<CategoryEntity> categoryEntities ) {
        List<Category> categories = new ArrayList<>();
        if (categoryEntities != null) {
            for (CategoryEntity currentEntity : categoryEntities) {
                Category current = new Category(currentEntity);
                categories.add(current);
            }
        }
        return categories;
    }
}
