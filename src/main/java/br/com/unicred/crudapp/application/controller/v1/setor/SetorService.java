package br.com.unicred.crudapp.application.controller.v1.setor;

import br.com.unicred.crudapp.domain.model.setor.Setor;

import java.util.List;

public interface SetorService {

    Setor salvar(Setor setor);

    Setor alterar(String id, Setor setor);

    void excluir(String id);

    Setor buscar(String id);

    List<Setor> listar();
}
