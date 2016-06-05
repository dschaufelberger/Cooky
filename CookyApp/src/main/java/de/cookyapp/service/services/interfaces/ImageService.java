package de.cookyapp.service.services.interfaces;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Dominik Schaufelberger on 05.06.2016.
 */
public interface ImageService {
    String writeImageThumbnail( byte[] imageData, Dimension boundary ) throws IOException;
}
