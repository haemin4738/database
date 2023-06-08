package com.koreatech.byeongcheonairlineapi.dto;


import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;
import lombok.Data;

import java.util.List;

@Data
public class FlightDto {
    private int flightId;
    private String departureTime;
    private int duration;
    private String arrivalTime;
    private String departure;
    private String arrival;
    private String planeName;
    private List<Seat> seats;
}
