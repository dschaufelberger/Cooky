package de.cookyapp.service.exceptions;

import java.security.PrivilegedActionException;

/**
 * Created by Dominik Schaufelberger on 20.04.2016.
 */
public class InvalidId extends IllegalArgumentException {
    private int id;

    /**
     * Constructs an <code>IllegalArgumentException</code> with no
     * detail message.
     */
    public InvalidId( int id ) {
        this.id = id;
    }

    /**
     * Constructs an <code>IllegalArgumentException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public InvalidId( String s, int id ) {
        super( s );
        this.id = id;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     * <p>
     * <p>Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link Throwable#getCause()} method).  (A <tt>null</tt> value
     *                is permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.5
     */
    public InvalidId( String message, Throwable cause, int id ) {
        super( message, cause );
        this.id = id;
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * PrivilegedActionException}).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link Throwable#getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.5
     */
    public InvalidId( Throwable cause, int id ) {
        super( cause );
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
