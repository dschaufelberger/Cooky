package de.cookyapp.service.services.interfaces;

import de.cookyapp.service.dto.Recipe;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jasper on 24.04.2016.
 */
public interface IImageService {

    Recipe saveImage (Recipe recipe, File image) throws IOException;

}
