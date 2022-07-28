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

    private final ModelMapper modelMapper;

    private final EmpresaRepository repository;

    @Autowired
    public EmpresaServiceImpl(final ModelMapper modelMapper, final EmpresaRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public EmpresaResponse criarEmpresa(final EmpresaRequest empresaRequest) {
        Empresa empresa = modelMapper.map(empresaRequest, Empresa.class);
        Empresa empresaSalva = repository.save(empresa);
        return modelMapper.map(empresaSalva, EmpresaResponse.class);
    }

    @Override
    public List<EmpresaResponse> listarTodasEmpresas() {
        List<Empresa> empresas = repository.findAll();
        List<EmpresaResponse> empresasResponse = new ArrayList<>();

        for(Empresa empresa : empresas) {
            EmpresaResponse empresaResponse = modelMapper.map(empresa, EmpresaResponse.class);
            empresasResponse.add(empresaResponse);
        }

        return empresasResponse;
    }

    @Override
    public EmpresaResponse buscarEmpresaPorId(final String id) {
        Optional<Empresa> empresa = repository.findById(new ObjectId(id));
        return modelMapper.map(empresa.get(), EmpresaResponse.class);
    }

}
