package com.koreatech.byeongcheonairlineapi.service;

import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;

import java.util.List;

public interface SeatService {

    List<Seat> findByFlight(int flightId);
}
