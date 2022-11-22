package br.com.unicred.crudapp.application.controller.v1.funcionario.dto.converter;

import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioRequest;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioResponse;
import br.com.unicred.crudapp.domain.model.funcionario.Funcionario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FuncionarioConverterImpl implements FuncionarioConverter {

    private final ModelMapper mapper;

    @Autowired
    public FuncionarioConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Funcionario converterParaFuncionario(final FuncionarioRequest funcionarioRequest) {
        return funcionarioRequest == null ? null : mapper.map(funcionarioRequest, Funcionario.class);
    }

    @Override
    public FuncionarioResponse converterParaResponse(final Funcionario funcionario) {
        return funcionario == null ? null : mapper.map(funcionario, FuncionarioResponse.class);
    }

    @Override
    public List<FuncionarioResponse> converterParaListaResponse(final List<Funcionario> funcionarios) {
        List<FuncionarioResponse> funcionariosResponse = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            funcionariosResponse.add(converterParaResponse(funcionario));
        }

        return funcionariosResponse;
    }
}
