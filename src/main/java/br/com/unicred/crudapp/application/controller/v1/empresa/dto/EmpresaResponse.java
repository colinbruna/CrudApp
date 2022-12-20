package br.com.unicred.crudapp.application.controller.v1.empresa.dto;

public class EmpresaResponse {

    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private EnderecoResponse endereco;

    public EmpresaResponse() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public EnderecoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(final EnderecoResponse endereco) {
        this.endereco = endereco;
    }
}
