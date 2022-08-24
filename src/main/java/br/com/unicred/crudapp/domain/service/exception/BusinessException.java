package br.com.unicred.crudapp.domain.service.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String exception) {
        super(exception);
    }
}