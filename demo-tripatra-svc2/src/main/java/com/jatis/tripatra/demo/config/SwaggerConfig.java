package com.jatis.tripatra.demo.config;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private static final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket api(@Value("${service.swagger-host:N/A}") String host) {
        log.info("--=-->>service.swagger-host="+host);
        if (host != null && !"N/A".equals(host)) {
                return new Docket(DocumentationType.SWAGGER_2)
                        .protocols(new HashSet<String>(Arrays.asList("http","https")))
                        .host(host)
                .select()
                .apis(RequestHandlerSelectors
                                .any())
                .paths(PathSelectors.any())
                .build();
        } else {
                return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors
                                .any())
                    .paths(PathSelectors.any())
                    .build();
        }
    }
}
