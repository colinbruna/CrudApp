package br.com.unicred.crudapp.domain.service.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(final String exception) {
        super(exception);
    }
}
