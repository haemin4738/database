package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;
import com.koreatech.byeongcheonairlineapi.mapper.SeatMapper;
import com.koreatech.byeongcheonairlineapi.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatMapper seatMapper;

    @Autowired
    public SeatServiceImpl(SeatMapper seatMapper) {
        this.seatMapper = seatMapper;
    }


    @Override
    public List<Seat> findByFlight(int flightId) {
        List<Seat> reservedSeats = seatMapper.getReservedSeatsByFlightId(flightId);
        for (Seat reservedSeat : reservedSeats) {
            reservedSeat.setId(reservedSeat.getId() % 201);
        }
        return reservedSeats;
    }
}
