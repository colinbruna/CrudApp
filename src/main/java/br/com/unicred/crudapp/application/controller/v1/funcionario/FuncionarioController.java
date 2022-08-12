package br.com.unicred.crudapp.application.controller.v1.funcionario;

import br.com.unicred.crudapp.application.controller.v1.exception.EntityNotFoundException;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioRequest;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.FuncionarioResponse;
import br.com.unicred.crudapp.application.controller.v1.funcionario.dto.converter.FuncionarioConverter;
import br.com.unicred.crudapp.domain.model.Funcionario;
import org.bson.types.ObjectId;
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
    public FuncionarioResponse alterar(@PathVariable String id, @RequestBody @Valid FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = converter.converterParaFuncionario(funcionarioRequest);
        Funcionario funcionarioAlterado = service.alterar(id, funcionario);

        if (Objects.isNull(funcionarioAlterado)) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        return converter.converterParaResponse(funcionarioAlterado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable String id) {
        Funcionario funcionario = service.buscar(id);

        if (Objects.isNull(funcionario)) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        service.exluir(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscar (@PathVariable String id) {
        Funcionario funcionario = service.buscar(id);

        if (Objects.isNull(funcionario)) {
            throw new EntityNotFoundException("Funcionário não encontrado");
        }

        return new ResponseEntity<>(converter.converterParaResponse(funcionario), HttpStatus.OK);
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
