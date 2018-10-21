package edu.keenank.advancedjava.utl;

/**
 *  Used to signal invalid XML or other JAXB related issues.
 */
public class XMLException extends Exception{

    /**
     * @param message describes the exception
     */
    public XMLException(String message) { super(message); }

    /**
     * Constructs a new exception with the specified detail message,
     * cause, suppression enabled or disabled, and writable stack
     * trace enabled or disabled.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     */
    public XMLException(String message, Throwable cause) { super(message, cause); }
}
