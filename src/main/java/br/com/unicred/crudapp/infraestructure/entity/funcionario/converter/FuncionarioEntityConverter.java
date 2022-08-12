package br.com.unicred.crudapp.infraestructure.entity.funcionario.converter;

import br.com.unicred.crudapp.domain.model.Funcionario;
import br.com.unicred.crudapp.infraestructure.entity.funcionario.FuncionarioEntity;

import java.util.List;

public interface FuncionarioEntityConverter {

    FuncionarioEntity converterParaEntity(Funcionario funcionario);

    Funcionario converterParaFuncionario(FuncionarioEntity funcionarioEntity);

    List<Funcionario> converterParaListaFuncionarios(List<FuncionarioEntity> funcionariosEntity);
}
