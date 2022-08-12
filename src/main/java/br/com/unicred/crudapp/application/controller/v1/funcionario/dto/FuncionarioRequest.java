package br.com.unicred.crudapp.application.controller.v1.funcionario.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

public class FuncionarioRequest {

    @NotBlank(message = "id_setor é obrigatório")
    private String idSetor;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @CPF
    @NotBlank(message = "cpf é obrigatório")
    private String cpf;

    public FuncionarioRequest() {
    }

    public String getIdSetor() { return idSetor; }

    public void setIdSetor(String idSetor) { this.idSetor = idSetor; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }
}
