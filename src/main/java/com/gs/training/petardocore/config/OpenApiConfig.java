package com.gs.training.petardocore.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new io.swagger.v3.oas.models.info.Info()
                .title("API Gestión de Personas de Afore")
                .version("1.0.0")
                .description("[ Base URL: http://localhost:8084/banco-azteca/afore/personas/v1]"));
    }
}
