package com.koreatech.byeongcheonairlineapi.service;

import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.dto.domain.Ticket;
import java.util.List;
import com.koreatech.byeongcheonairlineapi.mapper.TicketMapper;

public interface TicketService {
    List<Ticket> findAll();
    Ticket findByCustomerId(int id);
    Ticket findByMember(LoginDto loginDto);

    void insert(Ticket ticket);
}
