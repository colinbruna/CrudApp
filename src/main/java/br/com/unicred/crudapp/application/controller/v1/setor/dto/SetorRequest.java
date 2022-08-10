package br.com.unicred.crudapp.application.controller.v1.setor.dto;

import javax.validation.constraints.NotBlank;

public class SetorRequest {

    @NotBlank(message = "Nome é um campo obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é um campo obrigatório")
    private String descricao;

    @NotBlank(message = "Id da empresa é um campo obrigatório")
    private String idEmpresa;

    public SetorRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
