package br.com.unicred.crudapp.domain.service.impl;

import br.com.unicred.crudapp.application.dto.EmpresaDto;
import br.com.unicred.crudapp.application.dto.EnderecoDto;
import br.com.unicred.crudapp.domain.model.Empresa;
import br.com.unicred.crudapp.domain.model.Endereco;
import br.com.unicred.crudapp.domain.service.EmpresaService;
import br.com.unicred.crudapp.infraestructure.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaServiceImpl(final EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public List<EmpresaDto> listarTodasEmpresas() {
        return converterEmpresaParaDto(empresaRepository.findAll());
    }

    private List<EmpresaDto> converterEmpresaParaDto(final List<Empresa> empresas) {
        List<EmpresaDto> empresasDto = new ArrayList<>();

        for (Empresa empresa : empresas) {
            EmpresaDto empresaDto = new EmpresaDto();
            empresaDto.setId(empresa.getId().toString());
            empresaDto.setNome(empresa.getNome());
            empresaDto.setCnpj(empresa.getCnpj());
            empresaDto.setEmail(empresa.getEmail());
            empresaDto.setTelefone(empresa.getTelefone());
            empresaDto.setEndereco(converterEnderecoParaDto(empresa.getEndereco()));

            empresasDto.add(empresaDto);
        }

        return empresasDto;
    }

    private EnderecoDto converterEnderecoParaDto(final Endereco endereco) {
        EnderecoDto enderecoDto = new EnderecoDto();

        if (Objects.nonNull(endereco)) {
            enderecoDto.setLogradouro(endereco.getLogradouro());
            enderecoDto.setNumero(endereco.getNumero());
            enderecoDto.setBairro(endereco.getBairro());
            enderecoDto.setCidade(endereco.getCidade());
            enderecoDto.setEstado(endereco.getEstado());
            enderecoDto.setCep(endereco.getCep());
        }

        return enderecoDto;
    }

}
