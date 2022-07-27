package br.com.unicred.crudapp.infraestructure.repository;

import br.com.unicred.crudapp.domain.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpresaRepository extends MongoRepository<Empresa, Integer> {

}
