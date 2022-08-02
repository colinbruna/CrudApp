package br.com.unicred.crudapp.infraestructure.client;

public class ViaCepResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return complemento;
    }

    public void setNumero(String numero) { this.complemento = numero; }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return localidade;
    }

    public void setCidade(String cidade) {
        this.localidade = cidade;
    }

    public String getEstado() {
        return uf;
    }

    public void setEstado(String estado) {
        this.uf = estado;
    }
}
