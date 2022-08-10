package br.com.unicred.crudapp.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration                  //anotação que marca a classe como uma fonte de definições de bean
public class ModelMapperConfig {

    //exporta uma classe para o Spring, para ele carregar essa classe
    // e fazer a injeção de dependencia dela em outras classes
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
