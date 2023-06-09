package com.koreatech.byeongcheonairlineapi.dto.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseFlight {

    public ResponseFlight(String departureTime, String arrivalTime, String planeName, int availableSeats, String departure, String arrival, int duration, int flightId, int price) {
        this.departureTime = departureTime;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
        this.planeName = planeName;
        this.availableSeats = availableSeats;
        this.departure = departure;
        this.arrival = arrival;
        this.flightId = flightId;
        this.price = price;
    }
    private int price;
    private int flightId;
    private int duration;
    private String departureTime;
    private String arrivalTime;
    private String planeName;
    private int availableSeats;
    private String departure;
    private String arrival;
}
