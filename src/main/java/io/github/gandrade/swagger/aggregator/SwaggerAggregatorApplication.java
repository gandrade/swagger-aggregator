package io.github.gandrade.swagger.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableSwagger2

public class SwaggerAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerAggregatorApplication.class, args);
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(SwaggerAggregatorApplication.class.getPackage().getName()))
				.paths(PathSelectors.any())
				.build();
	}

	@Primary
	@Bean
	public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider) {
		return () -> {
			SwaggerResource wsResource = new SwaggerResource();
			wsResource.setName("HumanStore");
			wsResource.setSwaggerVersion("2.0");
			wsResource.setUrl("http://petstore.swagger.io/v2/swagger.json");

			SwaggerResource petStore = new SwaggerResource();
			petStore.setName("PetStore");
			petStore.setSwaggerVersion("2.0");
			petStore.setUrl("http://petstore.swagger.io/v2/swagger.json");

			List<SwaggerResource> resources = new ArrayList<>(defaultResourcesProvider.get());
			resources.add(wsResource);
			resources.add(petStore);
			return resources;
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("POST", "GET", "OPTIONS", "PUT");
			}
		};
	}
}
