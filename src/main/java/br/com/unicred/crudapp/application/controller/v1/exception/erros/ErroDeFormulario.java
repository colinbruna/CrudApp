package br.com.unicred.crudapp.application.controller.v1.exception.erros;

public class ErroDeFormulario {      //define o formato dos erros

    private final String campo;
    private final String mensagem;

    public ErroDeFormulario(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
