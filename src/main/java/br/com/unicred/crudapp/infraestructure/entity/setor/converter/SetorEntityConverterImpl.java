package br.com.unicred.crudapp.infraestructure.entity.setor.converter;

import br.com.unicred.crudapp.domain.model.setor.Setor;
import br.com.unicred.crudapp.infraestructure.entity.setor.SetorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetorEntityConverterImpl implements SetorEntityConverter{

    private final ModelMapper mapper;

    @Autowired
    public SetorEntityConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public SetorEntity converterParaEntity(Setor setor) {
        return mapper.map(setor, SetorEntity.class);
    }

    @Override
    public Setor converterParaSetor(SetorEntity setorEntity) {
        return mapper.map(setorEntity, Setor.class);
    }

    @Override
    public List<Setor> converterParaListaSetores(List<SetorEntity> setoresEntity) {
        List<Setor> setores = new ArrayList<>();

        for (SetorEntity setorEntity : setoresEntity) {
            setores.add(converterParaSetor(setorEntity));
        }

        return setores;
    }
}