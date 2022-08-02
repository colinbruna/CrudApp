package br.com.unicred.crudapp.exception;

public class FieldValidationException extends RuntimeException {  //exceção que estende a RuntimeException,

    private String campo;

    public FieldValidationException(final String campo, final String erro) {
        super(erro);
        this.campo = campo;
    }

    public String getCampo() { return campo; }

    public void setCampo(String campo) { this.campo = campo; }
}