package br.com.unicred.crudapp.application.controller.v1.empresa;

import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaRequest;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaResponse;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.converter.EmpresaConverter;
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

    @PostMapping
    public ResponseEntity<EmpresaResponse> salvar(@RequestBody @Valid EmpresaRequest empresaRequest) {
        Empresa empresa = converter.converterParaEmpresa(empresaRequest);
        Empresa empresaSalva = service.salvar(empresa);
        EmpresaResponse empresaResponse = converter.converterParaResponse(empresaSalva);
        return new ResponseEntity<>(empresaResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponse> alterar(@PathVariable String id, @RequestBody @Valid EmpresaRequest empresaRequest) {
        Empresa empresa = converter.converterParaEmpresa(empresaRequest);
        Empresa empresaAlterada = service.alterar(id,empresa);

        return empresaAlterada == null?
                ResponseEntity.notFound().build():
                ResponseEntity.ok(converter.converterParaResponse(empresaAlterada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaResponse> excluir(@PathVariable String id) {
        if (Objects.isNull(service.buscar(id))) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponse> buscar(@PathVariable String id) {
        Empresa empresa = service.buscar(id);

        return empresa == null?
                ResponseEntity.notFound().build():
                ResponseEntity.ok(converter.converterParaResponse(empresa));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponse>> listar() {
        List<Empresa> empresas = service.listar();
        return ResponseEntity.ok(converter.converterParaListaResponse(empresas));
    }
}