package br.com.unicred.crudapp.infraestructure.entity.empresa;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "empresa")
public class EmpresaEntity {

    @Id
    private ObjectId id;
    private String nome;
    private String cnpj;
    private String email;
    private String telefone;
    private EnderecoEntity endereco;

    public EmpresaEntity() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
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

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setEndereco(final EnderecoEntity endereco) {
        this.endereco = endereco;
    }
}
