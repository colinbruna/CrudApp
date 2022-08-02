package br.com.unicred.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CrudAppApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CrudAppApplication.class, args);
    }

}
