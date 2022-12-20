package br.com.unicred.crudapp.domain.service.empresa;

import br.com.unicred.crudapp.application.controller.v1.empresa.EmpresaService;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.domain.service.exception.BusinessException;
import br.com.unicred.crudapp.infraestructure.client.ViaCepClient;
import br.com.unicred.crudapp.infraestructure.client.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaAdapter adapter;
    private final ViaCepClient client;

    @Autowired
    public EmpresaServiceImpl(final EmpresaAdapter adapter, final ViaCepClient client) {
        this.adapter = adapter;
        this.client = client;
    }

    private void validarCep(final Empresa empresa) {
        if (!validarFormatoCep(empresa.getEndereco().getCep().replace("-", ""))) {
            throw new BusinessException("Cep inválido");
        }

        ViaCepResponse viaCepResponse = client.buscarCep(empresa.getEndereco().getCep());
        if (Objects.isNull(viaCepResponse.getCep())) {
            throw new BusinessException("Cep inválido");
        }
    }
    //validando o formato do cep usando expressão regular(padrão de pesquisa para strings)
    //avalia se o cep enviado combina com o padrão exigido
    private boolean validarFormatoCep(final String cep) {
        return cep.matches("\\d{8}");
    }

    private void validar(final Empresa empresa) {
        if (Objects.isNull(empresa.getNome())) {
            throw new BusinessException("Nome da empresa não informado");
        }
        validarCep(empresa);
    }

    @Override
    public Empresa salvar(final Empresa empresa) {
        validar(empresa);
        return adapter.salvar(empresa);
    }

    @Override
    public Empresa alterar(final String id, final Empresa empresa) {
        validar(empresa);
        return adapter.alterar(id, empresa);
    }

    @Override
    public void excluir(final String id) {
        adapter.excluir(id);
    }

    @Override
    public Empresa buscar(final String id) {
        return adapter.buscar(id);
    }

    @Override
    public List<Empresa> listar() {
        return adapter.listar();
    }
}
