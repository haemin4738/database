package com.koreatech.byeongcheonairlineapi.interceptor;


import com.koreatech.byeongcheonairlineapi.exception.UnAuthorizeException;
import com.koreatech.byeongcheonairlineapi.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
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
        if (request.getMethod().equals("OPTIONS")) return true; // preflight 요청이 인터셉터에 가로채지지 않도록 설정
        String accessToken = request.getHeader("access-token");
//        String contentType = request.getHeader("Content-Type");

//        log.debug("content-type: {}", contentType);
        log.debug("token: {}", accessToken);
        log.debug("userId : {}", jwtService.getInfo("account", accessToken));

        if (accessToken != null && jwtService.checkToken(accessToken)) return true;
        else {
            throw new UnAuthorizeException();
        }
    }
}
