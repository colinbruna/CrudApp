package br.com.unicred.crudapp.infraestructure.repository;

import br.com.unicred.crudapp.infraestructure.entity.EmpresaEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends MongoRepository<EmpresaEntity, ObjectId> {

}
