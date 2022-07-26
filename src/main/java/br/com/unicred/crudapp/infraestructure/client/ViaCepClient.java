package br.com.unicred.crudapp.infraestructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CepClient", url = "${cep.service.url}")
public interface ViaCepClient {

    @GetMapping(value = "{cep}/json/", produces = "application/json")
    ViaCepResponse buscarCep(@PathVariable String cep);
}
