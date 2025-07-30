package com.devmarcelino.courier.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
@Configuration
@SpringBootApplication
public class CourierManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourierManagementApplication.class, args);
	}

	@Bean
	public OpenAPI springOpenAi() {
		return new OpenAPI()
		.info(
			new Info().title("Courier Management API")
			.summary("API de entregadores")
			.description("RestAPI para gerenciamento de entregadores.")
			.version("1.0.0")
		);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v3/api-docs/**")
                        .allowedOrigins("http://localhost:9998")
                        .allowedMethods("GET");
            }
        };
    }
}
