package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.FlightDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.FlightModel;
import com.koreatech.byeongcheonairlineapi.mapper.FlightMapper;
import com.koreatech.byeongcheonairlineapi.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightMapper flightMapper;

    @Autowired
    public FlightServiceImpl(FlightMapper flightMapper) {
        this.flightMapper = flightMapper;
    }


    @Override
    public List<FlightDto> goTrip(FlightModel flightModel) {
        log.debug("flightModel {}" , flightModel);
        return flightMapper.findByCondition(flightModel);
    }

    @Override
    public List<FlightDto> comeHome(FlightModel flightModel) {
        // 출발지, 도착지 바꾸기.
        String departure = flightModel.getDeparture();
        flightModel.setDeparture(flightModel.getArrival());
        flightModel.setArrival(departure);
        // 출발일 가는날로 설정
        flightModel.setDepartureDate(flightModel.getArrivalDate());
        log.debug("flightModel {}" , flightModel);
        // 조건에 맞게 조회후 리턴.
        return flightMapper.findByCondition(flightModel);
    }
}
