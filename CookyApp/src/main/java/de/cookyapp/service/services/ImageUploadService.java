package de.cookyapp.service.services;

import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.services.interfaces.IImageUploadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Jasper on 24.04.2016.
 */
@Transactional
@Service
public class ImageUploadService implements IImageUploadService {
    @Override
    public Recipe saveImage( Recipe recipe, BufferedImage image ) throws IOException {
        Recipe currentRecipe = recipe;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( image, "jpg", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        currentRecipe.setImageFile( imageInByte );
        baos.close();

        return currentRecipe;
    }
}
