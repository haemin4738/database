package com.koreatech.byeongcheonairlineapi.service;

import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.ResponseTicket;
import com.koreatech.byeongcheonairlineapi.dto.domain.Ticket;
import java.util.List;
import com.koreatech.byeongcheonairlineapi.mapper.TicketMapper;

public interface TicketService {
    List<Ticket> findAll();
    List<ResponseTicket> findByCustomer(int id);
    List<ResponseTicket> findByMember(LoginDto loginDto);
    void insert(Ticket ticket);
    void updateState(Ticket ticket);
    void deleteById(int id);
}
