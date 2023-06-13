package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.CanceledTicketDto;
import com.koreatech.byeongcheonairlineapi.mapper.FlightMapper;
import com.koreatech.byeongcheonairlineapi.mapper.LocationMapper;
import com.koreatech.byeongcheonairlineapi.mapper.TicketMapper;
import com.koreatech.byeongcheonairlineapi.service.CancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CancelServiceImpl implements CancelService {

    private final TicketMapper ticketMapper;
    private final LocationMapper locationMapper;
    private final FlightMapper flightMapper;

    private final int PLUSHOUR = 2; // 결항 시간 기준을 결정. 현재시각 + PLUSHOUR 이내의 출발시각을 가진 티켓을 결항시킴.

    @Autowired
    public CancelServiceImpl(TicketMapper ticketMapper, LocationMapper locationMapper, FlightMapper flightMapper) {
        this.ticketMapper = ticketMapper;
        this.locationMapper = locationMapper;
        this.flightMapper = flightMapper;
    }

    @Override
    @Transactional
    public void cancelTicket() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

        //시간 더하기
        currentDateTime = currentDateTime.plusHours(PLUSHOUR);

        //Timestamp로 변환
        Timestamp timestamp = Timestamp.valueOf(currentDateTime);

        updateLocationRisk();
        updateFlightRisk();
        ticketMapper.editStateReservedToCanceled(timestamp);

    }

    @Override
    public void updateLocationRisk() {
        locationMapper.setRiskLevel();
    }

    @Override
    public void updateFlightRisk() {
        flightMapper.setRiskLevel(PLUSHOUR);
    }

    // 현재, 미래정보들에 대해서만 리셋. 테스트 전용
    @Override
    public void resetAll() {
        locationMapper.resetRiskLevel();
        flightMapper.resetRiskLevel();
        ticketMapper.editStateCanceledToReserved();
    }

    @Override
    public List<CanceledTicketDto> getCanceledTickets() {
        return ticketMapper.getCanceledTickets();
    }


}
