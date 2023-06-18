package com.koreatech.byeongcheonairlineapi.service;

import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.RequestCreateTicket;
import com.koreatech.byeongcheonairlineapi.dto.Model.ResponseTicket;
import com.koreatech.byeongcheonairlineapi.dto.domain.Ticket;
import java.util.List;

public interface TicketService {
    List<Ticket> findAll();
    List<ResponseTicket> findById(int id);
    List<ResponseTicket> findByMember(LoginDto loginDto);
    public void createCustomerTicket(RequestCreateTicket requestCreateTicket);
    public void createMemberTicket(RequestCreateTicket requestCreateTicket, String token);
    void updateState(Ticket ticket);
    void deleteById(int id);
}
