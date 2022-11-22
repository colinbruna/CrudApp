package br.com.unicred.crudapp.domain.model.setor;

public class Setor {

    private String idEmpresa;
    private String nome;
    private String descricao;

    public Setor() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(final String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
