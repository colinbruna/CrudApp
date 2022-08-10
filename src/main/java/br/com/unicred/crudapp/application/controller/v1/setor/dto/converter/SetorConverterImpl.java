package br.com.unicred.crudapp.application.controller.v1.setor.dto.converter;

import br.com.unicred.crudapp.application.controller.v1.setor.dto.SetorRequest;
import br.com.unicred.crudapp.application.controller.v1.setor.dto.SetorResponse;
import br.com.unicred.crudapp.domain.model.setor.Setor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetorConverterImpl implements SetorConverter {

    private final ModelMapper mapper;

    @Autowired
    public SetorConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Setor converterParaSetor(SetorRequest setorRequest) {
        return mapper.map(setorRequest, Setor.class);
    }

    @Override
    public SetorResponse converterParaResponse(Setor setor) {
        return mapper.map(setor, SetorResponse.class);
    }

    @Override
    public List<SetorResponse> converterParaListaResponse(List<Setor> setores) {
        List<SetorResponse> setoresResponse = new ArrayList<>();

        for (Setor setor : setores) {
            setoresResponse.add(converterParaResponse(setor));
        }
        return setoresResponse;
    }
}
