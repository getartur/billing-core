package com.getartur.billingcore.shared.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @SuppressWarnings("unchecked")
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.ignoredParameterTypes(Authentication.class)
                //.ignoredParameterTypes(Pageable.class)
                .select()
                .apis(Predicates.<RequestHandler>or(
                        RequestHandlerSelectors.basePackage("com.getartur.billingcore")
                ))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes( Optional.class )
                .securitySchemes(Arrays.asList(new ApiKey("apikey", "Authorization", "header")))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/anyPath.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("basic", authorizationScopes));
    }
}
