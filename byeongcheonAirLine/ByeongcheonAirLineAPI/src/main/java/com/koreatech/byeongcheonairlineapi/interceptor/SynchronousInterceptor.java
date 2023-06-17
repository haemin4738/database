package com.koreatech.byeongcheonairlineapi.interceptor;


import com.koreatech.byeongcheonairlineapi.mapper.TicketMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
@Slf4j
public class SynchronousInterceptor implements HandlerInterceptor {

    private final TicketMapper ticketMapper;
    @Autowired
    public SynchronousInterceptor(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (request.getMethod().equals("OPTIONS")) return true;
        try {
            ticketMapper.editStateReservedToUsed();
            log.debug("I USED!!!!!!!!");
        }catch (Exception e) {
            response.setStatus(500);
            return false;
        }
        return true;
    }
}
