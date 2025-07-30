package com.devmarcelino.delivery.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
@OpenAPIDefinition
@SpringBootApplication
@EnableDiscoveryClient
public class DeliveryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryManagementApplication.class, args);
	}

		@Bean
	public OpenAPI springOpenAi() {
		return new OpenAPI()
		.info(
			new Info().title("Delivery Management API")
			.summary("API de entregas")
			.description("RestAPI para gerenciamento de entregas.")
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
