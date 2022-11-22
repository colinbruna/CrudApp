package br.com.unicred.crudapp.application.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//anotacao que marca a classe como uma fonte de definicoes de bean
public class ModelMapperConfig {

    @Bean
    //exporta uma classe para o Spring, para ele carregar essa classe e fazer a
    // injecao de dependencia dela em outras classes
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
