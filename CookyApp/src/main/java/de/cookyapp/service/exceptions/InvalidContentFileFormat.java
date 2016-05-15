package de.cookyapp.service.exceptions;

/**
 * Created by Jasper on 03.05.2016.
 */
public class InvalidContentFileFormat extends RuntimeException {
    private String filename;
    private String contentType;

    public InvalidContentFileFormat( String filename, String contentType ) {
        this.filename = filename;
        this.contentType = contentType;
    }

    public InvalidContentFileFormat( String filename, String contentType, String message ) {
        super( message );
        this.filename = filename;
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }
}
