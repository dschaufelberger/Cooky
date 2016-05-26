package de.cookyapp.service.services;

import java.awt.*;
import java.awt.image.BufferedImage;

import de.cookyapp.service.services.interfaces.IImageScaling;
import org.springframework.stereotype.Service;

/**
 * Created by Dominik Schaufelberger on 26.05.2016.
 */
@Service
public class ImageScaling implements IImageScaling {
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
