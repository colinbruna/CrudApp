package br.com.unicred.crudapp.application.controller.v1.funcionario;

import br.com.unicred.crudapp.application.controller.v1.exception.EntityNotFoundException;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioRequest;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioResponse;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.converter.FuncionarioConverter;
import br.com.unicred.crudapp.domain.model.funcionario.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioConverter converter;
    private final FuncionarioService service;

    @Autowired
    public FuncionarioController(final FuncionarioConverter converter, FuncionarioService service) {
        this.converter = converter;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> salvar (@RequestBody @Valid FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = converter.converterParaFuncionario(funcionarioRequest);
        Funcionario funcionarioSalvo = service.salvar(funcionario);
        FuncionarioResponse funcionarioResponse = converter.converterParaResponse(funcionarioSalvo);
        return new ResponseEntity<>(funcionarioResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> alterar(@PathVariable String id, @RequestBody @Valid FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = converter.converterParaFuncionario(funcionarioRequest);
        Funcionario funcionarioAlterado = service.alterar(id, funcionario);

        return funcionario == null?
                ResponseEntity.notFound().build():
                ResponseEntity.ok(converter.converterParaResponse(funcionarioAlterado));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void excluir(@PathVariable String id) {
        if (Objects.isNull(service.buscar(id))) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        service.exluir(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscar(@PathVariable String id) {
        Funcionario funcionario = service.buscar(id);

        return funcionario == null?
                ResponseEntity.notFound().build():
                ResponseEntity.ok(converter.converterParaResponse(funcionario));
    }

    @GetMapping
    public List<FuncionarioResponse> listar() {
        List<Funcionario> funcionarios = service.listar();
        return converter.converterParaListaResponse(funcionarios);
    }

    @GetMapping("/setor/{idSetor}")
    public List<FuncionarioResponse> listarPorSetor(@PathVariable String idSetor) {
        List<Funcionario> funcionarios = service.listarPorSetor(idSetor);
        return converter.converterParaListaResponse(funcionarios);
    }

}
