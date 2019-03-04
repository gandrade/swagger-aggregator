package io.github.gandrade.aggregator;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

public class SwaggerBeanInitializer implements ApplicationContextInitializer<GenericApplicationContext> {

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        applicationContext.registerBean(SwaggerBeanInitializer.class, () -> this);
        applicationContext.registerBean(Docket.class,
                () -> applicationContext.getBean(SwaggerBeanInitializer.class).docket());
        applicationContext.registerBean(SwaggerResourcesProvider.class,
                () -> applicationContext.getBean(SwaggerBeanInitializer.class)
                        .swaggerResourcesProvider(applicationContext.getBean(InMemorySwaggerResourcesProvider.class)), bd ->
                        bd.setPrimary(true)
        );
    }

    public Docket docket() {
        String basePackage = SwaggerAggregatorApplication.class.getPackage().getName();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider inMemorySwaggerResourcesProvider) {
        return () -> {
            SwaggerResource wsResource = new SwaggerResource();
            wsResource.setName("HumanStore");
            wsResource.setSwaggerVersion("2.0");
            wsResource.setUrl("http://petstore.swagger.io/v2/swagger.json");

            SwaggerResource petStore = new SwaggerResource();
            petStore.setName("PetStore");
            petStore.setSwaggerVersion("2.0");
            petStore.setUrl("http://petstore.swagger.io/v2/swagger.json		");

            SwaggerResource dummy = new SwaggerResource();
            dummy.setName("Dummy");
            dummy.setSwaggerVersion("2.0");
            dummy.setUrl("http://pc00br28844.ad.esi.adp.com:8080/v2/api-docs");

            // TODO Collect all Swagger URLs from underlying services and add it as a SwaggerResource

            List<SwaggerResource> resources = new ArrayList<>();
            resources.add(wsResource);
            resources.add(petStore);
            resources.add(dummy);
            return resources;
        };
    }


//    @Bean
//    public Docket docketOld() {
//        String basePackage = SwaggerAggregatorApplication.class.getPackage().getName();
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage(basePackage))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Primary
//    @Bean
//    public SwaggerResourcesProvider swaggerResourcesProviderOld(InMemorySwaggerResourcesProvider inMemorySwaggerResourcesProvider) {
//        return () -> {
//            SwaggerResource wsResource = new SwaggerResource();
//            wsResource.setName("HumanStore");
//            wsResource.setSwaggerVersion("2.0");
//            wsResource.setUrl("http://petstore.swagger.io/v2/swagger.json");
//
//            SwaggerResource petStore = new SwaggerResource();
//            petStore.setName("PetStore");
//            petStore.setSwaggerVersion("2.0");
//            petStore.setUrl("http://petstore.swagger.io/v2/swagger.json		");
//
//            SwaggerResource dummy = new SwaggerResource();
//            dummy.setName("Dummy");
//            dummy.setSwaggerVersion("2.0");
//            dummy.setUrl("http://pc00br28844.ad.esi.adp.com:8080/v2/api-docs");
//
//            // TODO Collect all Swagger URLs from underlying services and add it as a SwaggerResource
//
//            List<SwaggerResource> resources = new ArrayList<>();
//            resources.add(wsResource);
//            resources.add(petStore);
//            resources.add(dummy);
//            return resources;
//        };
//    }


}
