package com.gagantk.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customSwaggerConfig() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Consumer Service")
						.description("This is a demo Spring Boot application to consume messages from Kafka broker"));
	}

}
