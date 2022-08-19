package br.com.unicred.crudapp.application.controller.v1.funcionario.dto.converter;

import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioRequest;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioResponse;
import br.com.unicred.crudapp.domain.model.funcionario.Funcionario;

import java.util.List;

public interface FuncionarioConverter {

    Funcionario converterParaFuncionario(FuncionarioRequest funcionarioRequest);

    FuncionarioResponse converterParaResponse(Funcionario funcionario);

    List<FuncionarioResponse> converterParaListaResponse(List<Funcionario> funcionarios);
}
