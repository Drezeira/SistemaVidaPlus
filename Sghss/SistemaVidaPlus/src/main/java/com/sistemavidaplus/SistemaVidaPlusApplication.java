package com.sistemavidaplus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Sistema VidaPlus API", version = "1.0", description = "API para o Sistema de Gestão Hospitalar VidaPlus"))
public class SistemaVidaPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaVidaPlusApplication.class, args);
    }
}