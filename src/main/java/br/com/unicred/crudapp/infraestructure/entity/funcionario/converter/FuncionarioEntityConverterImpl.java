package br.com.unicred.crudapp.infraestructure.entity.funcionario.converter;

import br.com.unicred.crudapp.domain.model.Funcionario;
import br.com.unicred.crudapp.infraestructure.entity.funcionario.FuncionarioEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FuncionarioEntityConverterImpl implements FuncionarioEntityConverter{

    private final ModelMapper mapper;

    @Autowired
    public FuncionarioEntityConverterImpl(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public FuncionarioEntity converterParaEntity(Funcionario funcionario) {
        return mapper.map(funcionario, FuncionarioEntity.class);
    }

    @Override
    public Funcionario converterParaFuncionario(FuncionarioEntity funcionarioEntity) {
        return mapper.map(funcionarioEntity, Funcionario.class);
    }

    @Override
    public List<Funcionario> converterParaListaFuncionarios(List<FuncionarioEntity> funcionariosEntity) {
        List<Funcionario> funcionarios = new ArrayList<>();

        for (FuncionarioEntity funcionarioEntity : funcionariosEntity) {
            funcionarios.add(converterParaFuncionario(funcionarioEntity));
        }

        return funcionarios;
    }
}
