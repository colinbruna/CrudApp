package br.com.unicred.crudapp.infraestructure.entity.setor.converter;

import br.com.unicred.crudapp.domain.model.setor.Setor;
import br.com.unicred.crudapp.infraestructure.entity.setor.SetorEntity;

import java.util.List;

public interface SetorEntityConverter {

    SetorEntity converterParaEntity(Setor setor);

    Setor converterParaSetor(SetorEntity setorEntity);

    List<Setor> converterParaListaSetores(List<SetorEntity> setoresEntity);
}
