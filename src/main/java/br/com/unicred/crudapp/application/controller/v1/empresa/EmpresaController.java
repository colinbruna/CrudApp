package br.com.unicred.crudapp.application.controller.v1.empresa;

import br.com.unicred.crudapp.application.controller.v1.exception.EntityNotFoundException;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.converter.EmpresaConverter;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaRequest;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaResponse;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService service;
    private final EmpresaConverter converter;

    @Autowired
    public EmpresaController(final EmpresaService service, final EmpresaConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping                    //ResponseEntity representa toda a resposta HTTP: código de status, cabeçalhos e corpo
    public ResponseEntity<EmpresaResponse> salvar(@RequestBody @Valid EmpresaRequest empresaRequest) {
        Empresa empresa = converter.converterParaEmpresa(empresaRequest);
        Empresa empresaSalva = service.salvar(empresa);
        EmpresaResponse empresaResponse = converter.converterParaResponse(empresaSalva);
        return new ResponseEntity<>(empresaResponse, HttpStatus.CREATED);
    }
    /*
    * variável empresa recebe a conversao da empresa request(recebida na requisição) para empresa
    * chama a service para salvar e passa a empresa
    * variavel empresa response recebe a conversao de empresa(que veio da service) para empresa response
    * retorna a empresa response e o status http created
     */

    @PutMapping("/{id}")
    public EmpresaResponse alterar(@PathVariable String id, @RequestBody @Valid EmpresaRequest empresaRequest) {
        Empresa empresa = converter.converterParaEmpresa(empresaRequest);           //empresa recebe a conversão de empresa request para empresa
        Empresa empresaAlterada = service.alterar(id,empresa);                      //empresa alterada recebe uma empresa da service

        if (Objects.isNull(empresaAlterada)) {
            throw new EntityNotFoundException("Empresa não encontrada");        //se o id da empresa alterada(recebida da service) for nulo devolver status not found
        }

        return converter.converterParaResponse(empresaAlterada);                //se o id for encontrado e a empresa alterada, converte empresa alterada para response
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String id) {
        Empresa empresa = service.buscar(id);

        if (Objects.isNull(empresa)) {
            throw new EntityNotFoundException("Empresa não encontrada, não será possível excluir"); //204 se conseguir excluir, 404 se não encontrar
        }

        service.excluir(id);
    }

    @GetMapping("/{id}")            //@PathVariable é utilizado quando o valor da variável é passada diretamente na URL como um valor que faz parte da URL
    public ResponseEntity<EmpresaResponse> buscar(@PathVariable String id) {
        Empresa empresa = service.buscar(id);                                                       //empresa recebe a empresa buscada na service

        if (Objects.isNull(empresa)) {
            throw new EntityNotFoundException("Empresa não encontrada");
        }

        return new ResponseEntity<>(converter.converterParaResponse(empresa), HttpStatus.OK);       //retorno: converte a empresa para response
    }

    @GetMapping
    public List<EmpresaResponse> listar() {
        List<Empresa> empresas = service.listar();                         //lista empresas recebe a lista de empresas chamada na service
        return converter.converterParaListaResponse(empresas);             //retorno: converte a lista de empresas para uma lista de empresas response
    }
}
