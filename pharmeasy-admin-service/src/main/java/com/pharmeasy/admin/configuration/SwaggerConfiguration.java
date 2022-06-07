package com.pharmeasy.admin.configuration;

import com.google.common.base.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
@Slf4j
public class SwaggerConfiguration {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api.*"), regex("/api.*"));
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Admin Service API")
                .description("Admin Service API reference for developers")
                .termsOfServiceUrl("https://medlife.com")
                .contact("Admin Service Management APi").license("License")
                .licenseUrl("test@123.com").version("1.0").build();
    }
}
