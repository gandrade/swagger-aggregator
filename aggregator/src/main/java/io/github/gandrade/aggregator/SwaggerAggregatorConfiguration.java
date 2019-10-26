package io.github.gandrade.aggregator;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EnableSwagger2
@Configuration
@EnableConfigurationProperties(SwaggerAggregatorConfigurationProperties.class)
public class SwaggerAggregatorConfiguration {


    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider,
                                                             SwaggerAggregatorConfigurationProperties swaggerAggregatorConfigurationProperties) {
        List<SwaggerAggregatorConfigurationProperties.SwaggerApplication> applications = swaggerAggregatorConfigurationProperties.getApplications();

        return () -> applications.stream().map(application -> {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(application.getName());
            swaggerResource.setSwaggerVersion(application.getVersion());
            swaggerResource.setUrl(application.getUrl());
            return swaggerResource;
        }).collect(Collectors.toList());
    }
}
