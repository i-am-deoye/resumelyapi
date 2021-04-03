package com.resumly.resumeapi.core.exceptions;

public class EntityNotCreatedException extends  RuntimeException{
    public EntityNotCreatedException(String message) {
        super(message);
    }
}
