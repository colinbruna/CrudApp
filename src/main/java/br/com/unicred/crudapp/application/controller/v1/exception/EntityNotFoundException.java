package br.com.unicred.crudapp.application.controller.v1.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String exception) {
        super(exception);
    }
}