package br.com.unicred.crudapp.application.controller.v1.funcionario;

import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioRequest;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioResponse;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.converter.FuncionarioConverter;
import br.com.unicred.crudapp.domain.model.funcionario.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioConverter converter;
    private final FuncionarioService service;

    @Autowired
    public FuncionarioController(final FuncionarioConverter converter, final FuncionarioService service) {
        this.converter = converter;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> salvar(
            @RequestBody @Valid final FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = converter.converterParaFuncionario(funcionarioRequest);
        Funcionario funcionarioSalvo = service.salvar(funcionario);
        FuncionarioResponse funcionarioResponse = converter.converterParaResponse(funcionarioSalvo);
        return new ResponseEntity<>(funcionarioResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> alterar(@PathVariable final String id,
                                                       @RequestBody @Valid final FuncionarioRequest funcionarioReq) {
        Funcionario funcionario = converter.converterParaFuncionario(funcionarioReq);
        Funcionario funcionarioAlterado = service.alterar(id, funcionario);

        return funcionarioAlterado == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(converter
                .converterParaResponse(funcionarioAlterado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable final String id) {
        if (Objects.isNull(service.buscar(id))) {
            return ResponseEntity.notFound().build();
        }

        service.exluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscar(@PathVariable final String id) {
        Funcionario funcionario = service.buscar(id);

        return funcionario == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(converter
                .converterParaResponse(funcionario));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> listar() {
        List<Funcionario> funcionarios = service.listar();
        return ResponseEntity.ok(converter.converterParaListaResponse(funcionarios));
    }

    @GetMapping("/setor/{idSetor}")
    public ResponseEntity<List<FuncionarioResponse>> listarPorSetor(@PathVariable final String idSetor) {
        List<Funcionario> funcionarios = service.listarPorSetor(idSetor);
        return ResponseEntity.ok(converter.converterParaListaResponse(funcionarios));
    }
}
