package br.com.unicred.crudapp.application.controller.v1.funcionario.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class FuncionarioRequest {

    @NotBlank(message = "id do Setor é obrigatório")
    private String idSetor;

    @NotBlank(message = "nome é obrigatório")
    private String nome;

    @CPF(message = "CPF inválido")
    @Pattern(regexp = "^\\d{11}$", message = "deve conter somente números")
    @NotBlank(message = "cpf é obrigatório")
    private String cpf;

    public FuncionarioRequest() {
    }

    public String getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(final String idSetor) {
        this.idSetor = idSetor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }
}
