package com.koreatech.byeongcheonairlineapi.dto.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseFlight {

    public ResponseFlight(String departureTime, String arrivalTime, String planeName, int availableSeats, String departure, String arrival, int duration) {
        this.departureTime = departureTime;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
        this.planeName = planeName;
        this.availableSeats = availableSeats;
        this.departure = departure;
        this.arrival = arrival;
    }
    private int duration;
    private String departureTime;
    private String arrivalTime;
    private String planeName;
    private int availableSeats;
    private String departure;
    private String arrival;
}
