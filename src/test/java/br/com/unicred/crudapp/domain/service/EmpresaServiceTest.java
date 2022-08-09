package br.com.unicred.crudapp.domain.service;

import br.com.unicred.crudapp.application.controller.v1.empresa.EmpresaService;
import br.com.unicred.crudapp.application.dto.EmpresaResponse;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.domain.service.empresa.EmpresaServiceImpl;
import br.com.unicred.crudapp.infraestructure.repository.EmpresaRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmpresaServiceTest {

    EmpresaService service;

    @Mock
    EmpresaRepository repository;

    @BeforeEach      //o código marcado com essa anotação é executado antes da cada teste
    public void setUp() {
        repository = mock(EmpresaRepository.class);
        service = new EmpresaServiceImpl(modelMapper, repository, adapterService, client);
    }

    @Test
    @DisplayName("Deve retornar uma lista de empresas")
    void retornarListaEmpresas() {
        when(repository.findAll()).thenReturn(listaEmpresasMock());

        List<EmpresaResponse> empresas = service.listar();

        assertEquals(1, empresas.size());
        assertEquals("62df09a4a9308e027d78139a", empresas.get(0).getId());
        assertEquals("Empresa", empresas.get(0).getNome());
        assertEquals("12345678912345", empresas.get(0).getCnpj());
        assertEquals("empresa@email.com", empresas.get(0).getEmail());
        assertEquals("123456789", empresas.get(0).getTelefone());
        //endereço
    }

    private List<Empresa> listaEmpresasMock() {
        List<Empresa> listaEmpresasMock = new ArrayList<>();

        Endereco enderecoMock = new Endereco();
        enderecoMock.setLogradouro("Rua rua");
        enderecoMock.setNumero(10);
        enderecoMock.setBairro("bairro");
        enderecoMock.setCidade("cidade");
        enderecoMock.setEstado("estado");
        enderecoMock.setCep("12345678");

        Empresa empresaMock = new Empresa();
        empresaMock.setId(new ObjectId("62df09a4a9308e027d78139a"));
        empresaMock.setNome("Empresa");
        empresaMock.setCnpj("12345678912345");
        empresaMock.setEmail("empresa@email.com");
        empresaMock.setTelefone("123456789");
        empresaMock.setEndereco(enderecoMock);

        listaEmpresasMock.add(empresaMock);

        return listaEmpresasMock;
    }

}
