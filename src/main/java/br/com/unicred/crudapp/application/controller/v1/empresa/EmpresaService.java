package br.com.unicred.crudapp.application.controller.v1.empresa;

import br.com.unicred.crudapp.domain.model.empresa.Empresa;

import java.util.List;

public interface EmpresaService {

    Empresa salvar(Empresa empresa);

    Empresa alterar(String id, Empresa empresa);

    void excluir(String id);

    Empresa buscar(String id);

    List<Empresa> listar();






}
