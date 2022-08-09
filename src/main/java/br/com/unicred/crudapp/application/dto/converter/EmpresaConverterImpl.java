package br.com.unicred.crudapp.application.dto.converter;

import br.com.unicred.crudapp.application.dto.EmpresaRequest;
import br.com.unicred.crudapp.application.dto.EmpresaResponse;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component                      //representa um componente (bean) que é detectado automaticamente
public class EmpresaConverterImpl implements EmpresaConverter {

    private final ModelMapper mapper;

    @Autowired
    public EmpresaConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Empresa converterParaEmpresa(final EmpresaRequest request) {
        return mapper.map(request, Empresa.class);
    }
    @Override
    public EmpresaResponse converterParaResponse(final Empresa empresa) {
        return mapper.map(empresa, EmpresaResponse.class);
    }

    @Override
    public List<EmpresaResponse> converterParaListaResponse(final List<Empresa> empresas) {
        List<EmpresaResponse> empresasResponse = new ArrayList<>();

        for(Empresa empresa : empresas) {
            empresasResponse.add(converterParaResponse(empresa));
        }

        return empresasResponse;
    }

}
