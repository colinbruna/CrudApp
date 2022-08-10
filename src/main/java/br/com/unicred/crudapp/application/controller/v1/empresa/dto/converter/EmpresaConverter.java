package br.com.unicred.crudapp.application.controller.v1.empresa.dto.converter;

import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaRequest;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaResponse;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;

import java.util.List;

public interface EmpresaConverter {

    Empresa converterParaEmpresa(EmpresaRequest empresaRequest);

    EmpresaResponse converterParaResponse(Empresa empresa);

    List<EmpresaResponse> converterParaListaResponse(List<Empresa> empresas);

}
