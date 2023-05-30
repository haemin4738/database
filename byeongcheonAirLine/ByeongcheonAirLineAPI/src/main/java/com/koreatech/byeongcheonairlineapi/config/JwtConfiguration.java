package com.koreatech.byeongcheonairlineapi.config;

import com.koreatech.byeongcheonairlineapi.interceptor.ConfirmInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtConfiguration implements WebMvcConfigurer {

    private final ConfirmInterceptor confirmInterceptor;

    @Autowired
    public JwtConfiguration(ConfirmInterceptor confirmInterceptor) {
        this.confirmInterceptor = confirmInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(confirmInterceptor)
                .addPathPatterns("/member/*")
                .excludePathPatterns("/member/login");
    }
}
