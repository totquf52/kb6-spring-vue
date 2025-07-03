package org.scoula.security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

// 보안 필터 등록하는 클래스 상속
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    // 문자셋 필터 설정 (UTF-8)
    private CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        // 인코딩 필터 및 파일 업로드 필터를 SpringSecurity 필터 체인 앞에 등록
        insertFilters(servletContext, encodingFilter(), new MultipartFilter());
    }
}