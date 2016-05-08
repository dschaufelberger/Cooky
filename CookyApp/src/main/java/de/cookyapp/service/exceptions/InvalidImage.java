package de.cookyapp.service.exceptions;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Jasper on 03.05.2016.
 */
public class InvalidImage extends RuntimeException {

    private MultipartFile image;

    public InvalidImage ( MultipartFile image) { this.image = image; }

    public InvalidImage (MultipartFile image, String s) {
        super (s);
        this.image = image;
    }

    public InvalidImage( String message, Throwable cause, MultipartFile image ) {
        super( message, cause );
        this.image = image;
    }

    public InvalidImage( Throwable cause, MultipartFile image ) {
        super( cause );
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }
}
