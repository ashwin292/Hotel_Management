package org.ashwin.projects.hotelmanagement.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {

        super(message);
    }
}
