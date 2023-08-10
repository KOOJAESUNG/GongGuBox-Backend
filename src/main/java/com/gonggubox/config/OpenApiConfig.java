package com.gonggubox.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger springdoc-ui 구성 파일
 */
@Configuration
public class OpenApiConfig {

//    @Bean
//    public GroupedOpenApi group1() {
//        return GroupedOpenApi.builder()
//                .group("1.ALL")
//                .pathsToMatch("/**")
//                // .packagesToScan("com.example.swagger") // package 필터 설정
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi group2() {
//        return GroupedOpenApi.builder()
//                .group("2.ADMIN")
//                .pathsToMatch("/adminRole/**","/googleOtp/**","/sse/**")
//                // .packagesToScan("com.example.swagger") // package 필터 설정
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi group3() {
//        return GroupedOpenApi.builder()
//                .group("3.MEMBER")
//                .pathsToMatch("/memberRole/**","/coolSms/**","/sse/**")
//                .build();
//    }

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info()
                .version("v1.0.0")
                .title("GONGGUBOX APP API Document");
//                .description("API Description");

        // SecuritySecheme명
        String jwtSchemeName = "JWT Token";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer"));
//                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}