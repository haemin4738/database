package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.FlightDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.ResponseFlight;
import com.koreatech.byeongcheonairlineapi.dto.Model.RequestFlight;
import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;
import com.koreatech.byeongcheonairlineapi.mapper.FlightMapper;
import com.koreatech.byeongcheonairlineapi.mapper.SeatMapper;
import com.koreatech.byeongcheonairlineapi.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

    private final Map<String, Integer> priceForSeatClass = new HashMap<>();

    {
        priceForSeatClass.put("economy", 1);
        priceForSeatClass.put("business", 2);
        priceForSeatClass.put("first", 3);
    }

    private final FlightMapper flightMapper;
    private final SeatMapper seatMapper;

    @Autowired
    public FlightServiceImpl(FlightMapper flightMapper, SeatMapper seatMapper) {
        this.flightMapper = flightMapper;
        this.seatMapper = seatMapper;
    }


    @Override
    public List<ResponseFlight> goTrip(RequestFlight requestFlight) {
        // 조회 조건에 맞는 항공편의 데이터, 좌석들 구하기.
        List<FlightDto> flightDtos = flightMapper.findByCondition(requestFlight);
        List<ResponseFlight> resultResponseFlights = new ArrayList<>();


        for (FlightDto flightDto : flightDtos) {
            int flightId = flightDto.getFlightId();

            List<Seat> reservedSeats = seatMapper.getReservedSeatsByFlightId(flightId);

//            Set<Seat> flightSeats = new HashSet<>(flightDto.getSeats());
            Set<Seat> flightSeats = flightDto.getSeats().stream()
                    .filter(a -> a.getSeatClass().equals(requestFlight.getSeatClass()))
                    .collect(Collectors.toSet());

            int availableSeats = flightSeats.size();

            for (Seat seat : reservedSeats) {
                if (flightSeats.contains(seat)) availableSeats--;
            }

            resultResponseFlights.add(new ResponseFlight(
                    flightDto.getDepartureTime(),
                    flightDto.getArrivalTime(),
                    flightDto.getPlaneName(),
                    availableSeats,
                    flightDto.getDeparture(),
                    flightDto.getArrival(),
                    flightDto.getDuration(),
                    flightDto.getFlightId(),
                    0
            ));

        }
        return resultResponseFlights;
    }

    @Override
    public List<ResponseFlight> comeHome(RequestFlight requestFlight) {
        // 출발지, 도착지 바꾸기.
        String departure = requestFlight.getDeparture();
        requestFlight.setDeparture(requestFlight.getArrival());
        requestFlight.setArrival(departure);
        // 출발일 가는날로 설정
        requestFlight.setDepartureDate(requestFlight.getArrivalDate());
        log.debug("flightModel {}" , requestFlight);
        // 조건에 맞게 조회후 리턴.
        return goTrip(requestFlight);
    }
}
