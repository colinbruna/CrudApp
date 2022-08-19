package br.com.unicred.crudapp.domain.service.empresa;

import br.com.unicred.crudapp.application.controller.v1.empresa.EmpresaService;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.domain.service.empresa.config.CepStub;
import br.com.unicred.crudapp.domain.service.exception.BusinessException;
import br.com.unicred.crudapp.infraestructure.client.ViaCepClient;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmpresaServiceImplTest {

    private static final WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8082));

    @Mock
    private EmpresaAdapter adapter;

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private CepStub cepStub;

    @BeforeAll
    static void setUp() {
        wireMockServer.start();
        configureFor("localhost", 8082);
    }

    @AfterAll
    static void setDown() {
        wireMockServer.stop();
    }

    // Estrutura básica de um método de teste: preparação do cenário, execução do cenário e a verificação de resultados

    @Test
    @DisplayName("Deve salvar uma empresa")
    void salvarSucesso() throws IOException {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        cepStub.consultaCepSucesso();

        when(adapter.salvar(any(Empresa.class))).thenReturn(empresa);
        Empresa empresaSalva = service.salvar(empresa);

        verify(adapter, times(1)).salvar(any(Empresa.class));

        assertNotNull(empresaSalva);
        assertEquals("Nome da empresa", empresaSalva.getNome());
        assertEquals("35690251000120", empresaSalva.getCnpj());
        assertEquals("empresa@email.com.br", empresaSalva.getEmail());
        assertEquals("33333333333", empresaSalva.getTelefone());
        assertEquals("91350300", empresaSalva.getCep());
        assertEquals("Rua empresa", empresaSalva.getLogradouro());
        assertEquals("10", empresaSalva.getComplemento());
        assertEquals("Bairro", empresaSalva.getBairro());
        assertEquals("Localidade", empresaSalva.getLocalidade());
        assertEquals("Uf", empresaSalva.getUf());
    }

    @Test
    @DisplayName("Deve lancar um BusinessException ao tentar salvar uma empresa sem informar o nome")
    void salvarNomeNaoInformado() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        empresa.setNome(null);

        BusinessException exception = assertThrows(BusinessException.class, () -> service.salvar(empresa));

        assertEquals("Nome", exception.getCampo());
        assertEquals("Nome não informado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lancar um BusinessException ao tentar salvar uma empresa com formato do cep invalido")
    void salvarCepFormatoInvalido() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        empresa.setCep("aaa");

        BusinessException exception = assertThrows(BusinessException.class, () -> service.salvar(empresa));

        assertEquals("Cep", exception.getCampo());
        assertEquals("Cep inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lancar um BusinessException ao tentar salvar uma empresa com cep inexistente")
    void salvarCepInexistente() throws IOException {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        empresa.setCep("00000000");
        cepStub.consultaCepInexistente();

        BusinessException exception = assertThrows(BusinessException.class, () -> service.salvar(empresa));

        assertEquals("Cep", exception.getCampo());
        assertEquals("Cep inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve alterar uma empresa")
    void alterarSucesso() throws IOException {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        cepStub.consultaCepSucesso();
        empresa.setNome("Nome da empresa alterado");

        when(adapter.alterar(anyString(), any(Empresa.class))).thenReturn(empresa);
        Empresa empresaAlterada = service.alterar("62f426a0ab9f3343c1e08807", empresa);

        verify(adapter, times(1)).alterar(anyString(), any(Empresa.class));

        assertNotNull(empresaAlterada);
        assertEquals("Nome da empresa alterado", empresaAlterada.getNome());
        assertEquals("35690251000120", empresaAlterada.getCnpj());
        assertEquals("empresa@email.com.br", empresaAlterada.getEmail());
        assertEquals("33333333333", empresaAlterada.getTelefone());
        assertEquals("91350300", empresaAlterada.getCep());
        assertEquals("Rua empresa", empresaAlterada.getLogradouro());
        assertEquals("10", empresaAlterada.getComplemento());
        assertEquals("Bairro", empresaAlterada.getBairro());
        assertEquals("Localidade", empresaAlterada.getLocalidade());
        assertEquals("Uf", empresaAlterada.getUf());
    }

    @Test
    @DisplayName("Deve excluir uma empresa")
    void excluir() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);

        doNothing().when(adapter).excluir(anyString());
        service.excluir("62f426a0ab9f3343c1e08807");

        verify(adapter, times(1)).excluir(anyString());
    }

    @Test
    @DisplayName("Deve buscar uma empresa por Id")
    void buscar() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);

        when(adapter.buscar(anyString())).thenReturn(criarEmpresaValida());
        Empresa empresa = service.buscar("62f426a0ab9f3343c1e08807");

        verify(adapter, times(1)).buscar(anyString());

        assertNotNull(empresa);
        assertEquals("Nome da empresa", empresa.getNome());
        assertEquals("35690251000120", empresa.getCnpj());
        assertEquals("empresa@email.com.br", empresa.getEmail());
        assertEquals("33333333333", empresa.getTelefone());
        assertEquals("91350300", empresa.getCep());
        assertEquals("Rua empresa", empresa.getLogradouro());
        assertEquals("10", empresa.getComplemento());
        assertEquals("Bairro", empresa.getBairro());
        assertEquals("Localidade", empresa.getLocalidade());
        assertEquals("Uf", empresa.getUf());
    }

    @Test
    @DisplayName("Deve retornar uma lista de empresas")
    void listar() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);

        when(adapter.listar()).thenReturn(criarListaEmpresaValida());

        List<Empresa> empresas = service.listar();

        assertNotNull(empresas); // assegurei que não é nulo
        assertEquals(2, empresas.size()); //assegurei que o tamanho da minha lista é 2
        assertEquals(Empresa.class, empresas.get(0).getClass()); //quero assegurar que o objeto do index 0 que veio dentro dessa lista tem a classe do tipo Empresa

        assertEquals("Nome da empresa", empresas.get(0).getNome());
        assertEquals("Nome da empresa2", empresas.get(1).getNome());
    }

    private Empresa criarEmpresaValida() {

        Empresa empresa = new Empresa();
        empresa.setNome("Nome da empresa");
        empresa.setCnpj("35690251000120");
        empresa.setEmail("empresa@email.com.br");
        empresa.setTelefone("33333333333");
        empresa.setCep("91350300");
        empresa.setLogradouro("Rua empresa");
        empresa.setComplemento("10");
        empresa.setBairro("Bairro");
        empresa.setLocalidade("Localidade");
        empresa.setUf("Uf");

        return empresa;
    }

    private List<Empresa> criarListaEmpresaValida() {
        List<Empresa> listaEmpresas = new ArrayList<>();

        Empresa empresa = new Empresa();
        empresa.setNome("Nome da empresa");
        empresa.setCnpj("35690251000120");
        empresa.setEmail("empresa@email.com.br");
        empresa.setTelefone("33333333333");
        empresa.setCep("91350300");
        empresa.setLogradouro("Rua empresa");
        empresa.setComplemento("10");
        empresa.setBairro("Bairro");
        empresa.setLocalidade("Localidade");
        empresa.setUf("Uf");

        Empresa empresa2 = new Empresa();
        empresa2.setNome("Nome da empresa2");
        empresa2.setCnpj("66626395000140");
        empresa2.setEmail("empresa2@email.com.br");
        empresa2.setTelefone("33333333333");
        empresa2.setCep("91350300");
        empresa2.setLogradouro("Rua empresa2");
        empresa2.setComplemento("10");
        empresa2.setBairro("Bairro2");
        empresa2.setLocalidade("Localidade2");
        empresa2.setUf("Uf2");

        listaEmpresas.add(empresa);
        listaEmpresas.add(empresa2);

        return listaEmpresas;
    }
}