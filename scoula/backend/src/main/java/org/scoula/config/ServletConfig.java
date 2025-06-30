package org.scoula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc // Spring MVC 기능 활성화
@ComponentScan(basePackages = {
        "org.scoula.exception",
        "org.scoula.controller",
        "org.scoula.board.controller",
        "org.scoula.member.controller"
})
public class ServletConfig implements WebMvcConfigurer {

    // 루트 경로 접근 시 index.html로 포워딩
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/resources/index.html");
    }

    // 정적 자원 매핑 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /resources/** 요청 → /resources/ 폴더에서 정적 자원 제공
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

        // /assets/** 요청 → /resources/assets/ 폴더에서 정적 자원 제공
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("/resources/assets/");

        // Swagger UI 리소스를 위한 핸들러 설정
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        // Swagger WebJar 리소스 설정
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // Swagger 리소스 설정
        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/v2/api-docs")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    // Servlet 3.0 파일 업로드 사용 시 - MultipartResolver 빈 등록
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
