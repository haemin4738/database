package com.koreatech.byeongcheonairlineapi.config;

import com.koreatech.byeongcheonairlineapi.interceptor.CanceledInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CancelConfiguration implements WebMvcConfigurer {

    private final CanceledInterceptor canceledInterceptor;

    @Autowired
    public CancelConfiguration(CanceledInterceptor canceledInterceptor) {
        this.canceledInterceptor = canceledInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(canceledInterceptor); // 사용할 인터셉터 추가

    }
}
