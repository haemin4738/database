package com.koreatech.byeongcheonairlineapi.interceptor;


import com.koreatech.byeongcheonairlineapi.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
@Slf4j
public class ConfirmInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;

    @Autowired
    public ConfirmInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
        String accessToken = request.getHeader("access-token");
        log.debug("token: {}", accessToken);
        log.debug("userId : {}", jwtService.getInfo("account", accessToken));
        if (jwtService.checkToken(accessToken)) return true;
        else {
//            response.sendError(HttpStatus.UNAUTHORIZED.value());
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "ACCESS TOKEN 만료! ACCESS TOKEN을 재발급 받으세요.");
            return false;
        }
    }
}
