package br.com.unicred.crudapp.application.controller.v1.setor.dto.converter;

import br.com.unicred.crudapp.application.controller.v1.setor.dto.SetorRequest;
import br.com.unicred.crudapp.application.controller.v1.setor.dto.SetorResponse;
import br.com.unicred.crudapp.domain.model.setor.Setor;

import java.util.List;

public interface SetorConverter {

    Setor converterParaSetor(SetorRequest setorRequest);

    SetorResponse converterParaResponse(Setor setor);

    List<SetorResponse> converterParaListaResponse(List<Setor> setores);
}
