package br.com.unicred.crudapp.domain.service;

import br.com.unicred.crudapp.application.controller.data.EmpresaRequest;
import br.com.unicred.crudapp.application.controller.data.EmpresaResponse;

import java.util.List;

public interface EmpresaService {

    EmpresaResponse criarEmpresa(EmpresaRequest empresaRequest);

    List<EmpresaResponse> listarTodasEmpresas();

    EmpresaResponse buscarEmpresaPorId(String id);

    EmpresaResponse alterarEmpresa(String id, EmpresaRequest empresaRequest);

    void excluirEmpresa(String id);
}
