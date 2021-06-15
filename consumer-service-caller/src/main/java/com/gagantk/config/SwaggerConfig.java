package com.gagantk.config;

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
				.info(new Info().title("Consumer Service Caller")
						.description("This is a demo Spring Boot application which provides aggregated count of messages from all consumer-service instances and also has Load Balancing"));
	}

}
