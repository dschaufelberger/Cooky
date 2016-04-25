package de.cookyapp.service.services;

import de.cookyapp.persistence.repositories.IRecipeCrudRepository;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.services.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jasper on 24.04.2016.
 */
@Transactional
@Service
public class ImageService implements IImageService {
    @Override
    public Recipe saveImage(Recipe recipe, File image) throws IOException {
        Recipe currentRecipe = recipe;
        FileInputStream inputStream = new FileInputStream(image);
        byte[] fileBytes = new byte[(int) image.length()];

        inputStream.read(fileBytes);
        currentRecipe.setImageFile(fileBytes);
        inputStream.close();

        return currentRecipe;
    }
}
