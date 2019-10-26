package io.github.gandrade.greetingservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfiguration  {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/v2/api-docs")
//            .allowedOrigins("*")
//            .allowedMethods("*").allowCredentials(true).allowedHeaders("*");
//    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Allow anyone and anything access. Probably ok for Swagger spec
        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:9090");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");

        source.registerCorsConfiguration("/v2/api-docs", config);
        return new CorsFilter(source);
    }
}
