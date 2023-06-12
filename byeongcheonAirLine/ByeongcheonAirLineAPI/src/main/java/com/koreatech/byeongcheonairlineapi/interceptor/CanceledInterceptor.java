package com.koreatech.byeongcheonairlineapi.interceptor;


import com.koreatech.byeongcheonairlineapi.service.CancelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
@Slf4j
public class CanceledInterceptor implements HandlerInterceptor {
    private final CancelService cancelService;

    @Autowired
    public CanceledInterceptor(CancelService cancelService) {
        this.cancelService = cancelService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        return true;
    }
}
