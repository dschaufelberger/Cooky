package de.cookyapp.service.services;

import de.cookyapp.persistence.repositories.IRecipeCrudRepository;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.services.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Jasper on 24.04.2016.
 */
@Transactional
@Service
public class ImageService implements IImageService {
    @Override
    public Recipe saveImage(Recipe recipe, BufferedImage image) throws IOException {
        /*Recipe currentRecipe = recipe;
        InputStream inputStream = image.ge;
        byte[] fileBytes = new byte[(int) image.length()];

        inputStream.read(fileBytes);
        currentRecipe.setImageFile(fileBytes);
        inputStream.close();*/

        Recipe currentRecipe = recipe;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( image, "jpg", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        currentRecipe.setImageFile(imageInByte);
        baos.close();

        return currentRecipe;
    }
}
