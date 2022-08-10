package br.com.unicred.crudapp.infraestructure.repository;

import br.com.unicred.crudapp.infraestructure.entity.setor.SetorEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends MongoRepository<SetorEntity, ObjectId> {

}
