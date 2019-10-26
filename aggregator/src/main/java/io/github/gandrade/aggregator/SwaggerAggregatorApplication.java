package io.github.gandrade.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SwaggerAggregatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerAggregatorApplication.class, args);
//        new SpringApplicationBuilder(SwaggerAggregatorApplication.class)
//                .initializers(new SwaggerBeanInitializer())
//                .run(args);
    }
}
