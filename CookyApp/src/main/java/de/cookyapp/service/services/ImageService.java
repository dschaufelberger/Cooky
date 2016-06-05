package de.cookyapp.service.services;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import de.cookyapp.service.services.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dominik Schaufelberger on 26.05.2016.
 */
@Service
public class ImageService implements IImageService {
    private ServletContext servletContext;

    @Autowired
    public ImageService( ServletContext servletContext ) {
        this.servletContext = servletContext;
    }

    @Override
    public BufferedImage scaleImageToBoundary( BufferedImage image, Dimension boundary ) {
        double scaleFactor = Math.min( 1d,
                getScaleFactorToFit( new Dimension( image.getWidth(), image.getHeight() ), boundary ) );

        int scaleWidth = (int) Math.round( image.getWidth() * scaleFactor );
        int scaleHeight = (int) Math.round( image.getHeight() * scaleFactor );

        Image scaled = image.getScaledInstance( scaleWidth, scaleHeight, Image.SCALE_SMOOTH );

        BufferedImage canvasImage = new BufferedImage( (int) boundary.getWidth(), (int) boundary.getHeight(), BufferedImage.TYPE_3BYTE_BGR );
        Graphics2D g = canvasImage.createGraphics();

        int x = (canvasImage.getWidth() - scaled.getWidth( null )) / 2;
        int y = (canvasImage.getHeight() - scaled.getHeight( null )) / 2;

        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, canvasImage.getWidth(), canvasImage.getHeight() );
        g.drawImage( scaled, x, y, null );
        g.dispose();

        return canvasImage;
    }

    @Override
    public String writeImageThumbnail( byte[] imageData, Dimension boundary ) throws IOException {
        String directoryPath = Paths.get( "resources", "images", "recipes" ).toString();
        String absoluteDirectoryPath = this.servletContext.getRealPath( directoryPath );
        createDirectoryIfNonexistant( absoluteDirectoryPath );

        String imageUUID = java.util.UUID.randomUUID().toString();
        String filename = imageUUID + ".jpg";
        String absoluteFilePath = absoluteDirectoryPath + File.separator + filename;

        InputStream inputStream = new ByteArrayInputStream( imageData );
        BufferedImage original = ImageIO.read( inputStream );
        BufferedImage scaled = scaleImageToBoundary( original, boundary );

        ImageIO.write( scaled, "jpg", new File( absoluteFilePath ) );

        String imageUrl = "/" + (directoryPath + "/" + filename).replace( File.separator, "/" );
        return imageUrl;
    }

    private void createDirectoryIfNonexistant( String absoluteDirectoryPath ) {
        File file = new File( absoluteDirectoryPath );
        if ( !file.exists() ) {
            file.mkdirs();
        }
    }

    private double getScaleFactorToFit( Dimension original, Dimension toFit ) {
        double scale = 1d;

        if ( original != null && toFit != null ) {
            double scaleWidth = getScaleFactor( (int) original.getWidth(), (int) toFit.getWidth() );
            double scaleHeight = getScaleFactor( (int) original.getHeight(), (int) toFit.getHeight() );

            scale = Math.min( scaleHeight, scaleWidth );
        }

        return scale;
    }

    private double getScaleFactor( int originalSize, int targetSize ) {
        double scale = 1d;

        if ( originalSize > targetSize ) {
            scale = (double) targetSize / (double) originalSize;
        } else {
            scale = (double) targetSize / (double) originalSize;
        }

        return scale;
    }
}
