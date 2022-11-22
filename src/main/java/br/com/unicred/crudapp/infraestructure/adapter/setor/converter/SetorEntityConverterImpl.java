package br.com.unicred.crudapp.infraestructure.adapter.setor.converter;

import br.com.unicred.crudapp.domain.model.setor.Setor;
import br.com.unicred.crudapp.infraestructure.entity.setor.SetorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetorEntityConverterImpl implements SetorEntityConverter {

    private final ModelMapper mapper;

    @Autowired
    public SetorEntityConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public SetorEntity converterParaEntity(final Setor setor) {
        return setor == null ? null : mapper.map(setor, SetorEntity.class);
    }

    @Override
    public Setor converterParaSetor(final SetorEntity setorEntity) {
        return setorEntity == null ? null : mapper.map(setorEntity, Setor.class);
    }

    @Override
    public List<Setor> converterParaListaSetores(final List<SetorEntity> setoresEntity) {
        List<Setor> setores = new ArrayList<>();

        for (SetorEntity setorEntity : setoresEntity) {
            setores.add(converterParaSetor(setorEntity));
        }

        return setores;
    }
}
