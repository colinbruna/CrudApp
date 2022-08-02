package br.com.unicred.crudapp.application.controller;

import br.com.unicred.crudapp.application.controller.data.EmpresaRequest;
import br.com.unicred.crudapp.application.controller.data.EmpresaResponse;
import br.com.unicred.crudapp.domain.service.EmpresaService;
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

    @Autowired                         //anotação para indicar que os parametros do construtor serão injetados
    public EmpresaController(final EmpresaService service) {
        this.service = service;
    }

    @PostMapping                    //ResponseEntity representa toda a resposta HTTP: código de status, cabeçalhos e corpo
    public ResponseEntity<EmpresaResponse> criarEmpresa(@RequestBody @Valid EmpresaRequest empresaRequest) {
        return new ResponseEntity<>(service.criarEmpresa(empresaRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public List<EmpresaResponse> listarTodasEmpresas() {
        return service.listarTodasEmpresas();
    }

    @GetMapping("/{id}")            //@PathVariable é utilizado quando o valor da variável é passada diretamente na URL como um valor que faz parte da URL
    public ResponseEntity<EmpresaResponse> buscarEmpresaPorId(@PathVariable String id) {//busca pode ser por cnpj?
        return new ResponseEntity<>(service.buscarEmpresaPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")                                            //poderia ser cnpj??
    public ResponseEntity<EmpresaResponse> alterarEmpresa(@PathVariable String id, @RequestBody @Valid EmpresaRequest empresaRequest) {
        EmpresaResponse empresaResponse = service.alterarEmpresa(id, empresaRequest);
        if (Objects.isNull(empresaResponse)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empresaResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirEmpresa(@PathVariable String id) {
        service.excluirEmpresa(id);
    }
}
