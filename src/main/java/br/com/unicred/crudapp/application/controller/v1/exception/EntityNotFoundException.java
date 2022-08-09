package br.com.unicred.crudapp.application.controller.v1.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(final String mensagem) {
        super(mensagem);
    }
}