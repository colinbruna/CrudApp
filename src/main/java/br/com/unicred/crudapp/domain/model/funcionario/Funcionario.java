package br.com.unicred.crudapp.domain.model.funcionario;

public class Funcionario {

    private String idSetor;
    private String nome;
    private String cpf;

    public Funcionario() {
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
