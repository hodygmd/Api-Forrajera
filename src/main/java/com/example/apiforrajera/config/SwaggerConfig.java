package com.example.apiforrajera.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig{

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("forrajera")
                .packagesToScan("com.example.apiforrajera.controllers")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Forrajera")
                        .description("API para el manejo de una forrajera")
                        .version("1.0")
                        .contact(new Contact().name("gmdhody")
                                .url("https://github.com/hodygmd")
                                .email("josemanuelmzamorano@gmail.com"))
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache License Version 2.0")));
    }
}
