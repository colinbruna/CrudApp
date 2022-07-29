package br.com.unicred.crudapp.domain.service;

import br.com.unicred.crudapp.application.data.EmpresaRequest;
import br.com.unicred.crudapp.application.data.EmpresaResponse;
import br.com.unicred.crudapp.domain.model.Empresa;
import br.com.unicred.crudapp.infraestructure.repository.EmpresaRepository;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final ModelMapper modelMapper;                                                      //converte objetos de entidades do modelo de domínio da aplicação em objetos do modelo de representação

    private final EmpresaRepository repository;

    @Autowired
    public EmpresaServiceImpl(final ModelMapper modelMapper, final EmpresaRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public EmpresaResponse criarEmpresa(final EmpresaRequest empresaRequest) {
        Empresa empresa = modelMapper.map(empresaRequest, Empresa.class);                       //recebe empresaRequest converte para empresa
        Empresa empresaSalva = repository.save(empresa);                                        //salva empresa
        return modelMapper.map(empresaSalva, EmpresaResponse.class);                            //converte empresa para empresaResponse e retorna ela
    }

    @Override
    public List<EmpresaResponse> listarTodasEmpresas() {
        List<Empresa> empresas = repository.findAll();                                          //buscando todas empresas do banco
        List<EmpresaResponse> empresasResponse = new ArrayList<>();                             //inicializa um ArrayList de empresasResponse

        for(Empresa empresa : empresas) {                                                       //Laço de repetição for: para cada empresa da lista empresas faça
            EmpresaResponse empresaResponse = modelMapper.map(empresa, EmpresaResponse.class);  //converte empresa para empresaResponse
            empresasResponse.add(empresaResponse);                                              //add empresaResponse na lista
        }

        return empresasResponse;                                                                //retorna a lista de empresas Response
    }

    @Override
    public EmpresaResponse buscarEmpresaPorId(final String id) {
        Optional<Empresa> empresa = repository.findById(new ObjectId(id));                      //busca no banco empresa pelo id
        return modelMapper.map(empresa.get(), EmpresaResponse.class);                           //converte empresa para empresa response e retorna a resposta
    }

    @Override
    public EmpresaResponse alterarEmpresa(final String id, final EmpresaRequest empresaRequest) {
        Optional<Empresa> optEmpresa = repository.findById(new ObjectId(id));                   //busca por id

        if (optEmpresa.isPresent()) {                                                           //se encontrar o id executar
            optEmpresa.get().setNome(empresaRequest.getNome());                                 //pega o nome na empresaRequest pelo getNome e altera na Empresa
            optEmpresa.get().setCnpj(empresaRequest.getCnpj());
            optEmpresa.get().setEmail(empresaRequest.getEmail());
            optEmpresa.get().setTelefone(empresaRequest.getTelefone());

            modelMapper.map(empresaRequest.getEndereco(),optEmpresa.get().getEndereco());       //pega os dados de endereço da empresaRequest e converte para endereço da optEmpresa

            /*optEmpresa.get().getEndereco().setLogradouro(empresaRequest.getEndereco().getLogradouro());
            optEmpresa.get().getEndereco().setNumero(empresaRequest.getEndereco().getNumero());
            optEmpresa.get().getEndereco().setBairro(empresaRequest.getEndereco().getBairro());
            optEmpresa.get().getEndereco().setCidade(empresaRequest.getEndereco().getCidade());
            optEmpresa.get().getEndereco().setEstado(empresaRequest.getEndereco().getEstado());
            optEmpresa.get().getEndereco().setCep(empresaRequest.getEndereco().getCep());*/

            return modelMapper.map(repository.save(optEmpresa.get()), EmpresaResponse.class);   //salva optEmpresa no banco converte para Response e retorna resposta
        }

        return null;                                                                            //se não encontrar o id, retorna null e trata na controller
    }

    @Override
    public void excluirEmpresa(final String id) {
        repository.deleteById(new ObjectId(id));                                                //exclui empresa por id
    }
}