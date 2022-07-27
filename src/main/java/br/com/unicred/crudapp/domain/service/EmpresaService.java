package br.com.unicred.crudapp.domain.service;

import br.com.unicred.crudapp.application.dto.EmpresaDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmpresaService {

    List<EmpresaDto> listarTodasEmpresas();


}
