package de.cookyapp.service.exceptions;

/**
 * Created by Dominik Schaufelberger on 24.05.2016.
 */
public class ImageUploadFailed extends RuntimeException {
    private String imageName;
    private String contentType;

    public ImageUploadFailed( String imageName, String contentType ) {
        this.imageName = imageName;
        this.contentType = contentType;
    }

    public ImageUploadFailed( String message, String imageName, String contentType ) {
        super( message );
        this.imageName = imageName;
        this.contentType = contentType;
    }

    public ImageUploadFailed( String message, String imageName, String contentType, Throwable cause ) {
        super( message, cause );
        this.imageName = imageName;
        this.contentType = contentType;
    }

    public ImageUploadFailed( String imageName, String contentType, Throwable cause ) {
        super( cause );
        this.imageName = imageName;
        this.contentType = contentType;
    }
}
