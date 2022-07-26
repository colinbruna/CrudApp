package br.com.unicred.crudapp.infraestructure.adapter.funcionario.converter;

import br.com.unicred.crudapp.domain.model.funcionario.Funcionario;
import br.com.unicred.crudapp.infraestructure.entity.funcionario.FuncionarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FuncionarioEntityConverterImpl implements FuncionarioEntityConverter {

    private final ModelMapper mapper;

    @Autowired
    public FuncionarioEntityConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public FuncionarioEntity converterParaEntity(final Funcionario funcionario) {
        return funcionario == null ? null : mapper.map(funcionario, FuncionarioEntity.class);
    }

    @Override
    public Funcionario converterParaFuncionario(final FuncionarioEntity funcionarioEntity) {
        return funcionarioEntity == null ? null : mapper.map(funcionarioEntity, Funcionario.class);
    }

    @Override
    public List<Funcionario> converterParaListaFuncionarios(final List<FuncionarioEntity> funcionariosEntity) {
        List<Funcionario> funcionarios = new ArrayList<>();

        for (FuncionarioEntity funcionarioEntity : funcionariosEntity) {
            funcionarios.add(converterParaFuncionario(funcionarioEntity));
        }

        return funcionarios;
    }
}
