package de.cookyapp.service.services.interfaces;

import de.cookyapp.service.dto.Category;

import java.util.List;

/**
 * Created by Jasper on 01.06.2016.
 */
public interface ICategoryCrudService {
    List<Category> getAllCategories ();
}
