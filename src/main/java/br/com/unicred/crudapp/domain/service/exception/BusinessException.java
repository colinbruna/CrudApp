package br.com.unicred.crudapp.domain.service.exception;

public class BusinessException extends RuntimeException {

    private final String campo;

    public BusinessException(final String campo, final String mensagem) {
        super(mensagem);
        this.campo = campo;
    }

    public String getCampo() { return campo; }
}