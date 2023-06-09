package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.SeatPriceDto;
import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;
import com.koreatech.byeongcheonairlineapi.mapper.FlightMapper;
import com.koreatech.byeongcheonairlineapi.mapper.SeatMapper;
import com.koreatech.byeongcheonairlineapi.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SeatServiceImpl implements SeatService {
    private final Map<String, Integer> priceForSeatClass = new HashMap<>();

    {
        priceForSeatClass.put("economy", 1);
        priceForSeatClass.put("business", 2);
        priceForSeatClass.put("first", 3);
    }
    private final SeatMapper seatMapper;
    private final FlightMapper flightMapper;

    @Autowired
    public SeatServiceImpl(SeatMapper seatMapper, FlightMapper flightMapper) {
        this.seatMapper = seatMapper;
        this.flightMapper = flightMapper;
    }


    @Override
    public List<Seat> findByFlight(int flightId) {
        List<Seat> reservedSeats = seatMapper.getReservedSeatsByFlightId(flightId);
        for (Seat reservedSeat : reservedSeats) {
            reservedSeat.setId(reservedSeat.getId() % 201);
        }
        return reservedSeats;
    }
    @Override
    public SeatPriceDto findPriceByFlight(int flightId){
        SeatPriceDto seatPriceDto = new SeatPriceDto();
        int flightPrice = flightMapper.findById(flightId).getPrice();
        seatPriceDto.setFirst(flightPrice * priceForSeatClass.get("first"));
        seatPriceDto.setBusiness(flightPrice * priceForSeatClass.get("business"));
        seatPriceDto.setEconomy(flightPrice * priceForSeatClass.get("economy"));
        return seatPriceDto;
    }
}
