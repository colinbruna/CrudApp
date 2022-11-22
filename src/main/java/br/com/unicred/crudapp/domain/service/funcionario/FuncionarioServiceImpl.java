package br.com.unicred.crudapp.domain.service.funcionario;

import br.com.unicred.crudapp.application.controller.v1.exception.EntityNotFoundException;
import br.com.unicred.crudapp.application.controller.v1.funcionario.FuncionarioService;
import br.com.unicred.crudapp.application.controller.v1.setor.SetorService;
import br.com.unicred.crudapp.domain.model.funcionario.Funcionario;
import br.com.unicred.crudapp.domain.model.setor.Setor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioAdapter adapter;
    private final SetorService setorService;

    @Autowired
    public FuncionarioServiceImpl(final FuncionarioAdapter adapter, final SetorService setorService) {
        this.adapter = adapter;
        this.setorService = setorService;
    }

    private void validarSetor(final Funcionario funcionario) {
        Setor idSetor = setorService.buscar(funcionario.getIdSetor());
        if (Objects.isNull(idSetor)) {
            throw new EntityNotFoundException("Setor não encontrado");
        }
    }

    @Override
    public Funcionario salvar(final Funcionario funcionario) {
        validarSetor(funcionario);
        return adapter.salvar(funcionario);
    }

    @Override
    public Funcionario alterar(final String id, final Funcionario funcionario) {
        validarSetor(funcionario);
        return adapter.alterar(id, funcionario);
    }

    @Override
    public void exluir(final String id) {
        adapter.excluir(id);
    }

    @Override
    public Funcionario buscar(final String id) {
        return adapter.buscar(id);
    }

    @Override
    public List<Funcionario> listar() {
        return adapter.listar();
    }

    @Override
    public List<Funcionario> listarPorSetor(final String idSetor) {
        return adapter.listarPorSetor(idSetor);
    }

}
