package com.exceptions;  // ✅ Defines the package where the class is stored

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to handle "Resource Not Found" scenarios.
 * This exception is thrown when an entity (e.g., blog, comment) does not exist in the database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)  // ✅ Sets the HTTP status to 404 when this exception is thrown
public class ResourceNotFoundException extends RuntimeException {  // ✅ Extends RuntimeException for unchecked exceptions

    /**
     * Constructor that accepts a custom message.
     *
     * @param message Error message describing the missing resource
     */
    public ResourceNotFoundException(String message) {
        super(message);  // ✅ Calls the parent class (RuntimeException) constructor with a custom message
    }
}
