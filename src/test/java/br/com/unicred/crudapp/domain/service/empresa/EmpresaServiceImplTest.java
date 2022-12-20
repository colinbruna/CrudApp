package br.com.unicred.crudapp.domain.service.empresa;

import br.com.unicred.crudapp.application.controller.v1.empresa.EmpresaService;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import br.com.unicred.crudapp.domain.model.empresa.Endereco;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmpresaServiceImplTest {

    private static final int PORT = 8082;
    private static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(
            WireMockConfiguration
                    .options()
                    .port(PORT));

    @Mock
    private EmpresaAdapter adapter;

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private CepStub cepStub;

    @BeforeAll
    static void setUp() {
        WIRE_MOCK_SERVER.start();
        configureFor("localhost", PORT);
    }

    @AfterAll
    static void setDown() {
        WIRE_MOCK_SERVER.stop();
    }

    // Estrutura básica de um método de teste: preparação do cenário, execução do cenário e a verificação de resultados

    @Test
    @DisplayName("Deve salvar uma empresa")
    void testeSalvarSucesso() throws IOException {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        cepStub.consultaCepSucesso();

        when(adapter.salvar(any(Empresa.class))).thenReturn(empresa);
        Empresa empresaSalva = service.salvar(empresa);

        verify(adapter, times(1)).salvar(any(Empresa.class));

        assertNotNull(empresaSalva);
        assertEquals(empresa.getNome(), empresaSalva.getNome());
        assertEquals(empresa.getCnpj(), empresaSalva.getCnpj());
        assertEquals(empresa.getEmail(), empresaSalva.getEmail());
        assertEquals(empresa.getTelefone(), empresaSalva.getTelefone());
        assertEquals(empresa.getEndereco().getCep(), empresaSalva.getEndereco().getCep());
        assertEquals(empresa.getEndereco().getLogradouro(), empresaSalva.getEndereco().getLogradouro());
        assertEquals(empresa.getEndereco().getComplemento(), empresaSalva.getEndereco().getComplemento());
        assertEquals(empresa.getEndereco().getBairro(), empresaSalva.getEndereco().getBairro());
        assertEquals(empresa.getEndereco().getLocalidade(), empresaSalva.getEndereco().getLocalidade());
        assertEquals(empresa.getEndereco().getUf(), empresaSalva.getEndereco().getUf());
    }

    @Test
    @DisplayName("Deve lancar um BusinessException ao tentar salvar uma empresa sem informar o nome")
    void testeSalvarNomeNaoInformado() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        empresa.setNome(null);

        BusinessException exception = assertThrows(BusinessException.class, () -> service.salvar(empresa));

        assertEquals("Nome da empresa não informado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lancar um BusinessException ao tentar salvar uma empresa com formato do cep invalido")
    void testeSalvarCepFormatoInvalido() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        empresa.getEndereco().setCep("aaa");

        BusinessException exception = assertThrows(BusinessException.class, () -> service.salvar(empresa));

        assertEquals("Cep inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lancar um BusinessException ao tentar salvar uma empresa com cep inexistente")
    void testeSalvarCepInexistente() throws IOException {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        empresa.getEndereco().setCep("00000000");
        cepStub.consultaCepInexistente();

        BusinessException exception = assertThrows(BusinessException.class, () -> service.salvar(empresa));

        assertEquals("Cep inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve alterar uma empresa")
    void testeAlterarSucesso() throws IOException {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa empresa = criarEmpresaValida();
        cepStub.consultaCepSucesso();

        when(adapter.alterar(anyString(), any(Empresa.class))).thenReturn(empresa);
        Empresa empresaAlterada = service.alterar("1", empresa);

        verify(adapter, times(1)).alterar(anyString(), any(Empresa.class));

        assertNotNull(empresaAlterada);
        assertEquals(empresa.getNome(), empresaAlterada.getNome());
        assertEquals(empresa.getCnpj(), empresaAlterada.getCnpj());
        assertEquals(empresa.getEmail(), empresaAlterada.getEmail());
        assertEquals(empresa.getTelefone(), empresaAlterada.getTelefone());
        assertEquals(empresa.getEndereco().getCep(), empresaAlterada.getEndereco().getCep());
        assertEquals(empresa.getEndereco().getLogradouro(), empresaAlterada.getEndereco().getLogradouro());
        assertEquals(empresa.getEndereco().getComplemento(), empresaAlterada.getEndereco().getComplemento());
        assertEquals(empresa.getEndereco().getBairro(), empresaAlterada.getEndereco().getBairro());
        assertEquals(empresa.getEndereco().getLocalidade(), empresaAlterada.getEndereco().getLocalidade());
        assertEquals(empresa.getEndereco().getUf(), empresaAlterada.getEndereco().getUf());
    }

    @Test
    @DisplayName("Deve excluir uma empresa")
    void testeExcluir() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);

        doNothing().when(adapter).excluir(anyString());
        service.excluir("1");

        verify(adapter, times(1)).excluir(anyString());
    }

    @Test
    @DisplayName("Deve buscar uma empresa por Id")
    void testeBuscar() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        Empresa expected = criarEmpresaValida();

        when(adapter.buscar(anyString())).thenReturn(expected);
        Empresa empresaEncontrada = service.buscar("1");

        verify(adapter, times(1)).buscar(anyString());

        assertNotNull(empresaEncontrada);
        assertEquals(expected.getNome(), empresaEncontrada.getNome());
        assertEquals(expected.getCnpj(), empresaEncontrada.getCnpj());
        assertEquals(expected.getEmail(), empresaEncontrada.getEmail());
        assertEquals(expected.getTelefone(), empresaEncontrada.getTelefone());
        assertEquals(expected.getEndereco().getCep(), empresaEncontrada.getEndereco().getCep());
        assertEquals(expected.getEndereco().getLogradouro(), empresaEncontrada.getEndereco().getLogradouro());
        assertEquals(expected.getEndereco().getComplemento(), empresaEncontrada.getEndereco().getComplemento());
        assertEquals(expected.getEndereco().getBairro(), empresaEncontrada.getEndereco().getBairro());
        assertEquals(expected.getEndereco().getLocalidade(), empresaEncontrada.getEndereco().getLocalidade());
        assertEquals(expected.getEndereco().getUf(), empresaEncontrada.getEndereco().getUf());
    }

    @Test
    @DisplayName("Deve retornar uma lista de empresas")
    void testeListar() {
        EmpresaService service = new EmpresaServiceImpl(adapter, viaCepClient);
        List<Empresa> listExpected = criarListaEmpresaValida();

        when(adapter.listar()).thenReturn(listExpected);

        List<Empresa> empresas = service.listar();

        assertNotNull(empresas); // assegurei que não é nulo
        assertEquals(2, empresas.size()); //assegurei que o tamanho da minha lista é 2
        assertEquals(Empresa.class, empresas.get(0).getClass());
        //quero assegurar que o objeto do index 0 que veio dentro dessa lista tem a classe do tipo Empresa

        assertEquals(listExpected.get(0).getNome(), empresas.get(0).getNome());
        assertEquals(listExpected.get(1).getNome(), empresas.get(1).getNome());
    }

    private Empresa criarEmpresaValida() {

        Endereco endereco = new Endereco();
        endereco.setCep("91350300");
        endereco.setLogradouro("Rua empresa");
        endereco.setComplemento("10");
        endereco.setBairro("Bairro");
        endereco.setLocalidade("Localidade");
        endereco.setUf("Uf");

        Empresa empresa = new Empresa();
        empresa.setNome("Nome da empresa");
        empresa.setCnpj("35690251000120");
        empresa.setEmail("empresa@email.com.br");
        empresa.setTelefone("33333333333");
        empresa.setEndereco(endereco);
        return empresa;
    }

    private List<Empresa> criarListaEmpresaValida() {
        List<Empresa> listaEmpresas = new ArrayList<>();

        Endereco endereco = new Endereco();
        endereco.setCep("91350300");
        endereco.setLogradouro("Rua empresa");
        endereco.setComplemento("10");
        endereco.setBairro("Bairro");
        endereco.setLocalidade("Localidade");
        endereco.setUf("Uf");

        Endereco endereco2 = new Endereco();
        endereco2.setCep("91350300");
        endereco2.setLogradouro("Rua empresa2");
        endereco2.setComplemento("10");
        endereco2.setBairro("Bairro2");
        endereco2.setLocalidade("Localidade2");
        endereco2.setUf("Uf2");

        Empresa empresa = new Empresa();
        empresa.setNome("Nome da empresa");
        empresa.setCnpj("35690251000120");
        empresa.setEmail("empresa@email.com.br");
        empresa.setTelefone("33333333333");
        empresa.setEndereco(endereco);

        Empresa empresa2 = new Empresa();
        empresa2.setNome("Nome da empresa2");
        empresa2.setCnpj("66626395000140");
        empresa2.setEmail("empresa2@email.com.br");
        empresa2.setTelefone("33333333333");
        empresa2.setEndereco(endereco2);

        listaEmpresas.add(empresa);
        listaEmpresas.add(empresa2);

        return listaEmpresas;
    }
}
