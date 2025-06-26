package org.scoula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2 // Swagger2 활성화
public class SwaggerConfig {

    // 문서 기본 정보
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API 문서")
                .description("Swagger + JWT 인증 설정")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                // JWT 인증 설정 추가
                .securityContexts(List.of(this.securityContext()))   // SecurityContext 설정
                .securitySchemes(List.of(this.apiKey()))            // ApiKey 설정
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(
                        org.springframework.web.bind.annotation.RestController.class)) // REST API만 문서화
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo()); // 문서 정보
    }

    // JWT SecurityContext 구성
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    // 인증 범위 설정 (global로 전체 적용)
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }

    // JWT를 위한 ApiKey 설정
    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }
}