package br.com.unicred.crudapp.domain.service.setor;

import br.com.unicred.crudapp.domain.model.setor.Setor;

import java.util.List;

public interface SetorAdapter {

    Setor salvar(Setor setor);

    Setor alterar(String id, Setor setor);

    void excluir(String id);

    Setor buscar(String id);

    List<Setor> listar();
}
