package br.com.unicred.crudapp.application.dto.converter;

import br.com.unicred.crudapp.application.dto.EmpresaRequest;
import br.com.unicred.crudapp.application.dto.EmpresaResponse;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;

import java.util.List;

public interface EmpresaConverter {

    Empresa converterParaEmpresa(EmpresaRequest empresaRequest);

    EmpresaResponse converterParaResponse(Empresa empresa);

    List<EmpresaResponse> converterParaListaResponse(List<Empresa> empresas);

}
