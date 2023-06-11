package com.koreatech.byeongcheonairlineapi.interceptor;

import com.koreatech.byeongcheonairlineapi.service.CancelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class CanceledInterceptor implements HandlerInterceptor {
    private final CancelService cancelService;

    @Autowired
    public CanceledInterceptor(CancelService cancelService) {
        this.cancelService = cancelService;
    }
}
