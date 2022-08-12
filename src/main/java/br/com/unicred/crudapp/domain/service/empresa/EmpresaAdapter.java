package br.com.unicred.crudapp.domain.service.empresa;

import br.com.unicred.crudapp.domain.model.Empresa;

import java.util.List;

public interface EmpresaAdapter {

    Empresa salvar(Empresa empresa);

    Empresa alterar(String id, Empresa empresa);

    void excluir(String id);

    Empresa buscar(String id);

    List<Empresa> listar();
}
