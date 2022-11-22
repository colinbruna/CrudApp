package br.com.unicred.crudapp.application.controller.v1.setor;

import br.com.unicred.crudapp.application.controller.v1.setor.dto.SetorRequest;
import br.com.unicred.crudapp.application.controller.v1.setor.dto.SetorResponse;
import br.com.unicred.crudapp.application.controller.v1.setor.dto.converter.SetorConverter;
import br.com.unicred.crudapp.domain.model.setor.Setor;
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
@RequestMapping("/setores")
public class SetorController {

    private final SetorConverter converter;
    private final SetorService service;

    @Autowired
    public SetorController(final SetorConverter converter, final SetorService service) {
        this.converter = converter;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SetorResponse> salvar(final @RequestBody @Valid SetorRequest setorRequest) {
        Setor setor = converter.converterParaSetor(setorRequest);
        Setor setorSalvo = service.salvar(setor);
        SetorResponse setorResponse = converter.converterParaResponse(setorSalvo);
        return new ResponseEntity<>(setorResponse, HttpStatus.CREATED);
    }
    /*
    * Primeiro fiz uma busca pela empresa para cadastrar uma empresa válida no setor,
    * encontrando a empresa, converto o setor request para setor para chamar a service e salvar
    * retornando da service converto para setor response e uma resposta http created
    */

    @PutMapping("/{id}")
    public ResponseEntity<SetorResponse> alterar(@PathVariable final String id,
                                                 @RequestBody @Valid final SetorRequest setorRequest) {
        Setor setor = converter.converterParaSetor(setorRequest);
        Setor setorAlterado = service.alterar(id, setor);

        return setorAlterado == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(converter
                .converterParaResponse(setorAlterado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable final String id) {
        if (Objects.isNull(service.buscar(id))) {
            return ResponseEntity.notFound().build();
        }

        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorResponse> buscar(@PathVariable final String id) {
        Setor setor = service.buscar(id);

        return setor == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(converter
                .converterParaResponse(setor));
    }

    @GetMapping
    public ResponseEntity<List<SetorResponse>> listar() {
        List<Setor> setores = service.listar();
        return ResponseEntity.ok(converter.converterParaListaResponse(setores));
    }
}
