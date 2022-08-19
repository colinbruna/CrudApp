package br.com.unicred.crudapp.infraestructure.adapter.funcionario;

import br.com.unicred.crudapp.domain.model.funcionario.Funcionario;
import br.com.unicred.crudapp.domain.service.funcionario.FuncionarioAdapter;
import br.com.unicred.crudapp.infraestructure.entity.funcionario.FuncionarioEntity;
import br.com.unicred.crudapp.infraestructure.adapter.funcionario.converter.FuncionarioEntityConverter;
import br.com.unicred.crudapp.infraestructure.repository.funcionario.FuncionarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioAdapterGatewayImpl implements FuncionarioAdapter {

    private final FuncionarioEntityConverter converter;
    private final FuncionarioRepository repository;

    @Autowired
    public FuncionarioAdapterGatewayImpl(final FuncionarioEntityConverter converter, final FuncionarioRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public Funcionario salvar(final Funcionario funcionario) {
        FuncionarioEntity funcionarioEntity = converter.converterParaEntity(funcionario);
        FuncionarioEntity funcionarioEntitySalvo = repository.save(funcionarioEntity);
        return converter.converterParaFuncionario(funcionarioEntitySalvo);
    }

    @Override
    public Funcionario alterar(final String id, final Funcionario funcionario) {
        Optional<FuncionarioEntity> optFuncionarioEntity = repository.findById(new ObjectId(id));

        if (optFuncionarioEntity.isPresent()) {
            FuncionarioEntity entity = converter.converterParaEntity(funcionario);
            entity.setId(optFuncionarioEntity.get().getId());
            return converter.converterParaFuncionario(repository.save(entity));
        }

        return null;
    }

    @Override
    public void excluir(String id) {
        repository.deleteById(new ObjectId(id));
    }

    @Override
    public Funcionario buscar(final String id) {
        Optional<FuncionarioEntity> optFuncionarioEntity = repository.findById(new ObjectId(id));

        if (optFuncionarioEntity.isEmpty()) {
            return null;
        }

        return converter.converterParaFuncionario(optFuncionarioEntity.get());
    }

    @Override
    public List<Funcionario> listar() {
        List<FuncionarioEntity> funcionariosEntity = repository.findAll();
        return converter.converterParaListaFuncionarios(funcionariosEntity);
    }

    @Override
    public List<Funcionario> listarPorSetor(String idSetor) {
        List<FuncionarioEntity> funcionariosEntity = repository.findByIdSetor(idSetor);
        return converter.converterParaListaFuncionarios(funcionariosEntity);
    }

}
