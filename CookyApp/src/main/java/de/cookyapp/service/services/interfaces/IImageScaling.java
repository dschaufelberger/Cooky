package de.cookyapp.service.services.interfaces;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Dominik Schaufelberger on 26.05.2016.
 */
public interface IImageScaling {
    BufferedImage scaleImageToBoundary( BufferedImage image, Dimension boundary );
}
