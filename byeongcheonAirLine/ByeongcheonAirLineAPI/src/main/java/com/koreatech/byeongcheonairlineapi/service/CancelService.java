package com.koreatech.byeongcheonairlineapi.service;

import com.koreatech.byeongcheonairlineapi.dto.CanceledTicketDto;

import java.util.List;

public interface CancelService {

    void cancelTicket();
    void updateLocationRisk();
    void updateFlightRisk();

    void resetAll();

    List<CanceledTicketDto> getCanceledTickets();

}
