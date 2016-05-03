package de.cookyapp.service.services;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.repositories.IRecipeCrudRepository;
import de.cookyapp.service.dto.Recipe;
import de.cookyapp.service.services.interfaces.IImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
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

    IRecipeCrudRepository recipeCrudRepository;

    @Autowired
    public ImageUploadService (IRecipeCrudRepository recipeCrudRepository) {
        this.recipeCrudRepository = recipeCrudRepository;
    }

    @Override
    public void saveImage( int recipeId, BufferedImage image ) throws IOException {
        RecipeEntity currentRecipe = recipeCrudRepository.findOne( recipeId );
        //Repo entity laden

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( image, "jpg", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        currentRecipe.setImageFile( imageInByte );
        baos.close();

        recipeCrudRepository.save( currentRecipe );
    }
}
