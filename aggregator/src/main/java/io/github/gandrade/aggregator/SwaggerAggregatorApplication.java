package io.github.gandrade.aggregator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SwaggerAggregatorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SwaggerAggregatorApplication.class)
                .initializers(new SwaggerBeanInitializer())
                .run(args);
    }
}
