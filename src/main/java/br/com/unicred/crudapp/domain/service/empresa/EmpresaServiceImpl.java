package br.com.unicred.crudapp.domain.service.empresa;

import br.com.unicred.crudapp.application.controller.v1.empresa.EmpresaService;
import br.com.unicred.crudapp.domain.service.exception.BusinessException;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.infraestructure.client.ViaCepClient;
import br.com.unicred.crudapp.infraestructure.client.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        if (!validarFormatoCep(empresa.getCep().replace("-", ""))) {        //se o método de validar formato der falso, lançar exceção,
            throw new BusinessException("Cep", "Cep inválido");                                      //senão seguir o fluxo e chamar o metodo buscar cep
        }

        ViaCepResponse viaCepResponse = client.buscarCep(empresa.getCep());                   //buscando cep
        if (Objects.isNull(viaCepResponse.getCep())) {                                        //se o cep for nulo, lançar excessão de erro de erro de campo
            throw new BusinessException("Cep", "Cep inválido");                               //cep válido segue o fluxo no método que chamou o validar cep
        }
    }

    private boolean validarFormatoCep(final String cep) {                //validando o formato do cep usando expressão regular(padrão de pesquisa para strings,)
        return cep.matches("\\d{8}");                              //avalia se o cep enviado combina com o padrão exigido
    }

    @Override
    public Empresa salvar(final Empresa empresa) {
        validarCep(empresa);                                //somente as regras de negócio, nesse caso por enquanto só estou validando o cep
        return adapter.salvar(empresa);               //chama a adapter service para criar a empresa
    }

    @Override
    public Empresa alterar(final String id, final Empresa empresa) {
        validarCep(empresa);
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