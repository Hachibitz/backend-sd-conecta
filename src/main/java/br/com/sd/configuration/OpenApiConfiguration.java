package br.com.sd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(info = 
@Info(title = "SD Conecta API",
	version = "v1",
	description = "Documentation of SD Conecta API"))
public class OpenApiConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(
					new io.swagger.v3.oas.models.info.Info()
					.title("SD Conecta API")
						.version("v1")
						.license(
								new io.swagger.v3.oas.models.info.License()
								.name("Apache 2.0")
								.url("http://springdoc.org")));
	}
}
