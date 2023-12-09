package com.server.booksummar.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Feedback Books")
                        .description("API de gerenciamento de resumos de livros")
                        .version("1.1.0")
                        .termsOfService("Termo de uso: Open Source")
                        
                ).externalDocs(
                        new ExternalDocumentation())
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

    }

    @SecurityScheme(
            name = "bearerAuth",
            type = SecuritySchemeType.HTTP,
            scheme = "bearer",
            bearerFormat = "JWT"
    )
    static class BearerAuth {
    }
}