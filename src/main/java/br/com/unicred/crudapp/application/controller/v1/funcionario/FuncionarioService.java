package br.com.unicred.crudapp.application.controller.v1.funcionario;

import br.com.unicred.crudapp.domain.model.Funcionario;
import org.bson.types.ObjectId;

import java.util.List;

public interface FuncionarioService {

    Funcionario salvar(Funcionario funcionario);

    Funcionario alterar(String id, Funcionario funcionario);

    void exluir(String id);

    Funcionario buscar(String id);

    List<Funcionario> listar();

    List<Funcionario> listarPorSetor(String idSetor);
}
