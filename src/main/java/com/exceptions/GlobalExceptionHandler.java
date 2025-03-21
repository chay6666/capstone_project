package com.exceptions;  // Declares that this class belongs to the 'com.exceptions' package.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice  // ✅ Global exception handler for all REST controllers.
public class GlobalExceptionHandler {
    
    // ✅ Handles validation errors for request body fields.
    @ExceptionHandler(MethodArgumentNotValidException.class)  // Triggers when validation fails for request body.
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();  // Creates a list to store error messages.

        // Loops through all validation errors in the request.
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField(); // Extracts the field that caused the error.
            String errorMessage = error.getDefaultMessage();    // Extracts the error message.
            details.add(fieldName + "|" + errorMessage);        // Stores field name and message in the list.
        });

        // Returns a BAD_REQUEST (400) response with validation error details.
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    // ✅ Handles Resource Not Found errors (e.g., when an entity is missing in the database).
    @ExceptionHandler(ResourceNotFoundException.class)  // Triggers when a requested resource is not found.
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> response = new HashMap<>(); // Creates a map to structure the error response.
        
        response.put("error", true);                    // Indicates an error occurred.
        response.put("message", ex.getMessage());       // Includes the custom error message.
        response.put("status", HttpStatus.NOT_FOUND.value());  // Sets the HTTP status code (404).

        // Returns a NOT_FOUND (404) response with structured JSON.
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // ✅ Handles Constraint Violations (e.g., invalid query parameters).
    @ExceptionHandler(ConstraintViolationException.class)  // Triggers when request parameters fail validation.
    public String handleCVException(ConstraintViolationException ex) {
        return ex.getMessage();  // Returns the error message directly as a plain text response.
    }
}
