package com.example.bookshop.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SpringfoxConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.ant("/api/*"))
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfo(
                "Bookshop API",
                "API for Bookstore",
                "1.0",
                "http://www.awesometerms.org",
                new Contact("API owner", "http://www.superapi.org", "owner@superapi.org"),
                "apiSuperLicense",
                "http://www.awesomelicense.org",
                new ArrayList<>()
        );
    }
}
