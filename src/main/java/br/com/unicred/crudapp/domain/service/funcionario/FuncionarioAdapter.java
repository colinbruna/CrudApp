package br.com.unicred.crudapp.domain.service.funcionario;

import br.com.unicred.crudapp.domain.model.funcionario.Funcionario;

import java.util.List;

public interface FuncionarioAdapter {

    Funcionario salvar(Funcionario funcionario);

    Funcionario alterar(String id, Funcionario funcionario);

    void excluir(String id);

    Funcionario buscar(String id);

    List<Funcionario> listar();

    List<Funcionario> listarPorSetor(String idSetor);
}
