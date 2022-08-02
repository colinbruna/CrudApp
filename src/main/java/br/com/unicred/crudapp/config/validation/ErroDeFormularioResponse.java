package br.com.unicred.crudapp.config.validation;

public class ErroDeFormularioResponse {                         //Essa é a classe que representará um erro de validação

    private final String campo;
    private final String erro;

    public ErroDeFormularioResponse(final String campo, final String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
