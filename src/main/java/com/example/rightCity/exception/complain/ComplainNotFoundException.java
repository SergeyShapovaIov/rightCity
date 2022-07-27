package com.example.rightCity.exception.complain;

public class ComplainNotFoundException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public ComplainNotFoundException() {
        super("Complain not found!");
    }
}
