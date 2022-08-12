package br.com.unicred.crudapp.application.controller.v1.funcionario.dto;

public class FuncionarioResponse {

    private String idSetor;
    private String nome;
    private String cpf;

    public FuncionarioResponse() {
    }

    public String getIdSetor() { return idSetor; }

    public void setIdSetor(String idSetor) { this.idSetor = idSetor; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }
}
