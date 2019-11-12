package com.javainuse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author rich
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket newsApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.any())
	            .paths(PathSelectors.any())
	            .build()
	            .securitySchemes(Lists.newArrayList(apiKey()))
	            .securityContexts(Lists.newArrayList(securityContext()))
	            //.apiInfo(generateApiInfo());
;
	}

	/*
	 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
	 * .select()
	 * .apis(RequestHandlerSelectors.basePackage("com.javainuse.controller"))
	 * .paths(PathSelectors.any()) .build() .apiInfo(apiInfo())
	 * .securitySchemes(Arrays.asList(apiKey())); }
	 */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("The REST API for demo swagger.").termsOfServiceUrl("\"http://vidyalaya.000webhostapp.com/#!/")
                .contact(new Contact("Raghavendra J", "", "Raghavendrajraaghu33@gmail.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .version("0.0.1")
                .build();
    }

     
	/*
	 * private ApiKey apiKey() { return new ApiKey("Bearer", "Authorization",
	 * "header"); } private SecurityContext securityContext() { return
	 * SecurityContext.builder().securityReferences(defaultAuth())
	 * .forPaths(PathSelectors.any()).build(); }
	 * 
	 * private List<SecurityReference> defaultAuth() { AuthorizationScope
	 * authorizationScope = new AuthorizationScope( "global", "accessEverything");
	 * AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	 * authorizationScopes[0] = authorizationScope; return Arrays.asList(new
	 * SecurityReference("Bearer", authorizationScopes)); }
	 */

    
    @Bean
    SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
}