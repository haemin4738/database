package com.koreatech.byeongcheonairlineapi.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FlightDto {
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private int duration;
    private String planeName;
    private int availableSeats;
}
