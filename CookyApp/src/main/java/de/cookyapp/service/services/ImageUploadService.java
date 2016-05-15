package de.cookyapp.service.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import de.cookyapp.persistence.entities.RecipeEntity;
import de.cookyapp.persistence.repositories.app.IRecipeCrudRepository;
import de.cookyapp.service.services.interfaces.IImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jasper on 24.04.2016.
 */
@Transactional
@Service
public class ImageUploadService implements IImageUploadService {

    IRecipeCrudRepository recipeCrudRepository;

    @Autowired
    public ImageUploadService( IRecipeCrudRepository recipeCrudRepository ) {
        this.recipeCrudRepository = recipeCrudRepository;
    }

    @Override
    public void saveImage( int recipeId, BufferedImage image ) throws IOException {
        if ( recipeCrudRepository.findOne( recipeId ) != null && image != null ) {
            RecipeEntity currentRecipe = recipeCrudRepository.findOne( recipeId );

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( image, "jpg", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            currentRecipe.setImageFile( imageInByte );
            baos.close();

            recipeCrudRepository.save( currentRecipe );
        }
    }
}
