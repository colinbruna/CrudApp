package br.com.unicred.crudapp.infraestructure.entity.funcionario;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "funcionario")
public class FuncionarioEntity {

    private ObjectId id;
    private String idSetor;
    private String nome;
    private String cpf;

    public FuncionarioEntity() {
    }

    public ObjectId getId() { return id; }

    public void setId(ObjectId id) { this.id = id; }

    public String getIdSetor() { return idSetor; }

    public void setIdSetor(String idSetor) { this.idSetor = idSetor; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }
}
