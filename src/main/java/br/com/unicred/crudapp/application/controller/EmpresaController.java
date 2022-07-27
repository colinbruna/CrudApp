package br.com.unicred.crudapp.application.controller;

import br.com.unicred.crudapp.application.dto.EmpresaDto;
import br.com.unicred.crudapp.domain.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(final EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public List<EmpresaDto> listarTodasEmpresas() {
        return empresaService.listarTodasEmpresas();
    }




}
