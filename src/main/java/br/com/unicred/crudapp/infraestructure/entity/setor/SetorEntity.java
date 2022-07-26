package br.com.unicred.crudapp.infraestructure.entity.setor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "setor")
public class SetorEntity {

    @Id
    private ObjectId id;
    private String idEmpresa;
    private String nome;
    private String descricao;

    public SetorEntity() {
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
