package br.com.unicred.crudapp.application.controller.v1.empresa.dto.converter;

import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaRequest;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaResponse;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component                      //representa um componente (bean) que e detectado automaticamente
public class EmpresaConverterImpl implements EmpresaConverter {

    private final ModelMapper mapper;

    @Autowired
    public EmpresaConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Empresa converterParaEmpresa(final EmpresaRequest empresaRequest) {
        return empresaRequest == null ? null : mapper.map(empresaRequest, Empresa.class);
    }

    @Override
    public EmpresaResponse converterParaResponse(final Empresa empresa) {
        return empresa == null ? null : mapper.map(empresa, EmpresaResponse.class);
    }

    @Override
    public List<EmpresaResponse> converterParaListaResponse(final List<Empresa> empresas) {
        List<EmpresaResponse> empresasResponse = new ArrayList<>();

        for (Empresa empresa : empresas) {
            empresasResponse.add(converterParaResponse(empresa));
        }

        return empresasResponse;
    }
}
