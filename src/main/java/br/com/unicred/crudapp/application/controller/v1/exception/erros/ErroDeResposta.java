package br.com.unicred.crudapp.application.controller.v1.exception.erros;

public class ErroDeResposta {

    private final String mensagem;

    public ErroDeResposta(String mensagem) {
        this.mensagem = mensagem;
    }

        public String getMensagem() {
        return mensagem;
    }
}
