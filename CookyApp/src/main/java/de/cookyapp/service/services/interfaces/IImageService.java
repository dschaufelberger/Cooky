package de.cookyapp.service.services.interfaces;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Dominik Schaufelberger on 26.05.2016.
 */
public interface IImageService {
    BufferedImage scaleImageToBoundary( BufferedImage image, Dimension boundary );

    String writeImageThumbnail( byte[] imageData, Dimension boundary ) throws IOException;
}
