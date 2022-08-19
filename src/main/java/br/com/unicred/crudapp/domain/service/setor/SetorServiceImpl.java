package br.com.unicred.crudapp.domain.service.setor;

import br.com.unicred.crudapp.application.controller.v1.empresa.EmpresaService;
import br.com.unicred.crudapp.application.controller.v1.exception.EntityNotFoundException;
import br.com.unicred.crudapp.application.controller.v1.setor.SetorService;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.domain.model.setor.Setor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class SetorServiceImpl implements SetorService {

    private final SetorAdapter adapter;
    private final EmpresaService empresaService;

    @Autowired
    public SetorServiceImpl(final SetorAdapter adapter, final EmpresaService empresaService) {
        this.adapter = adapter;
        this.empresaService = empresaService;
    }

    private void validarEmpresa(final Setor setor) {
        Empresa idEmpresa = empresaService.buscar(setor.getIdEmpresa());
        if (Objects.isNull(idEmpresa)) {
            throw new EntityNotFoundException("Empresa não encontrada");
        }
    }

    @Override
    public Setor salvar(final Setor setor) {
        validarEmpresa(setor);
        return adapter.salvar(setor);
    }

    @Override
    public Setor alterar(final String id, final Setor setor) {
        validarEmpresa(setor);
        return adapter.alterar(id, setor);
    }

    @Override
    public void excluir(final String id) {
        adapter.excluir(id);
    }

    @Override
    public Setor buscar(final String id) {
        return adapter.buscar(id);
    }

    @Override
    public List<Setor> listar() {
        return adapter.listar();
    }
}