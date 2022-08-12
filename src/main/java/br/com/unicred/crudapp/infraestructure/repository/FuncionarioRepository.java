package br.com.unicred.crudapp.infraestructure.repository;

import br.com.unicred.crudapp.infraestructure.entity.funcionario.FuncionarioEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends MongoRepository<FuncionarioEntity, ObjectId> {

    List<FuncionarioEntity> findByIdSetor(String idSetor); //método criado seguindo o padrão de nomenclatura do spring data

}
