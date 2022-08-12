package br.com.unicred.crudapp.infraestructure.adapter;

import br.com.unicred.crudapp.domain.model.Setor;
import br.com.unicred.crudapp.domain.service.setor.SetorAdapter;
import br.com.unicred.crudapp.infraestructure.entity.setor.SetorEntity;
import br.com.unicred.crudapp.infraestructure.entity.setor.converter.SetorEntityConverter;
import br.com.unicred.crudapp.infraestructure.repository.SetorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetorAdapterGatewayImpl implements SetorAdapter {

    private final SetorEntityConverter converter;
    private final SetorRepository repository;

    @Autowired
    public SetorAdapterGatewayImpl(final SetorEntityConverter converter, final SetorRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    public Setor salvar(final Setor setor) {
        SetorEntity setorEntity = converter.converterParaEntity(setor);
        SetorEntity setorEntitySalvo = repository.save(setorEntity);
        return converter.converterParaSetor(setorEntitySalvo);
    }

    @Override
    public Setor alterar(final String id, final Setor setor) {
        Optional<SetorEntity> optSetorEntity = repository.findById(new ObjectId(id));

        if (optSetorEntity.isPresent()) {
            SetorEntity entity = converter.converterParaEntity(setor);
            entity.setId(optSetorEntity.get().getId());
            return converter.converterParaSetor(repository.save(entity));
        }

        return null;
    }

    @Override
    public void excluir(final String id) {
        repository.deleteById(new ObjectId(id));
    }

    @Override
    public Setor buscar(final String id) {
        Optional<SetorEntity> optSetorEntity = repository.findById(new ObjectId(id));

        if (optSetorEntity.isEmpty()) {
            return null;
        }

        return converter.converterParaSetor(optSetorEntity.get());
    }

    @Override
    public List<Setor> listar() {
        List<SetorEntity> setoresEntity = repository.findAll();
        return converter.converterParaListaSetores(setoresEntity);
    }
}