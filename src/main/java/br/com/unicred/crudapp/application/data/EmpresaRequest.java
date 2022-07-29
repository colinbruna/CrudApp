package br.com.unicred.crudapp.application.data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EmpresaRequest {

    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String cnpj;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String telefone;
    @NotNull
    private EnderecoResponse endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponse endereco) {
        this.endereco = endereco;
    }
}