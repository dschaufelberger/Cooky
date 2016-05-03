package de.cookyapp.service.services.interfaces;

import de.cookyapp.service.dto.Recipe;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jasper on 24.04.2016.
 */
public interface IImageUploadService {

    void saveImage( int recipeId, BufferedImage image ) throws IOException;

}
