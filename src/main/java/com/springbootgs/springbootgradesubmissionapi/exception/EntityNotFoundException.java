package com.springbootgs.springbootgradesubmissionapi.exception;

public class EntityNotFoundException extends RuntimeException { 

    // Rational: Creating a 'UserNotFoundException has repetitive logic as the other not found exceptions'
    // for the sake of avoiding repetitive code, this constructor accepts a class type. getSimpleName() extracts the name of the class
    public EntityNotFoundException(Long id, Class<?> entity) {
            super("The " + entity.getSimpleName().toLowerCase() + " with id '" + id + "' does not exist in our records");
    }

}
