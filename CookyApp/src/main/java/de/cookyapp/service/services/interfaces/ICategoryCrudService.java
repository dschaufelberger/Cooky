package de.cookyapp.service.services.interfaces;

import java.util.List;

import de.cookyapp.service.dto.Category;

/**
 * Created by Jasper on 01.06.2016.
 */
public interface ICategoryCrudService {
    List<Category> getAllCategories();
}
