package br.com.unicred.crudapp.application.controller.v1.empresa;

import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaRequest;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.EmpresaResponse;
import br.com.unicred.crudapp.application.controller.v1.empresa.dto.converter.EmpresaConverter;
import br.com.unicred.crudapp.application.controller.v1.exception.erros.ExceptionResponse;
import br.com.unicred.crudapp.domain.model.empresa.Empresa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/empresas")
@Tag(name = "CrudApp", description = "Integração com Empresa")
public class EmpresaController {

    private final EmpresaService service;
    private final EmpresaConverter converter;

    @Autowired
    public EmpresaController(final EmpresaService service, final EmpresaConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping
    @Operation(summary = "Salvar cadastro nova empresa")
    @ApiResponse(responseCode = "201", description = "Created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaResponse.class))
    })
    @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
    })
    public ResponseEntity<EmpresaResponse> salvar(@RequestBody @Valid final EmpresaRequest empresaRequest) {
        Empresa empresa = converter.converterParaEmpresa(empresaRequest);
        Empresa empresaSalva = service.salvar(empresa);
        EmpresaResponse empresaResponse = converter.converterParaResponse(empresaSalva);
        return new ResponseEntity<>(empresaResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponse> alterar(@PathVariable final String id,
                                                   @RequestBody @Valid final EmpresaRequest empresaRequest) {
        Empresa empresa = converter.converterParaEmpresa(empresaRequest);
        Empresa empresaAlterada = service.alterar(id, empresa);

        return empresaAlterada == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(converter
                        .converterParaResponse(empresaAlterada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaResponse> excluir(@PathVariable final String id) {
        if (Objects.isNull(service.buscar(id))) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponse> buscar(
            @Parameter(description = "id", example = "63c6d5ca5578ec03877be13e")
            @PathVariable final String id) {
        Empresa empresa = service.buscar(id);

        return empresa == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(converter.
                converterParaResponse(empresa));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponse>> listar() {
        List<Empresa> empresas = service.listar();
        return ResponseEntity.ok(converter.converterParaListaResponse(empresas));
    }
}
