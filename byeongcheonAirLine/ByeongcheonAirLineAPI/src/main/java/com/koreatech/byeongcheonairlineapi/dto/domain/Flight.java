package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Flight {
    private int id;
    private int planeId;
    private int departureId;
    private int arrivalId;
    private Timestamp departureTime;
    private long duration;
    private int price;
    private int riskLevel;
    public Flight(int planeId, int departureId, int arrivalId, Timestamp departureTime, long duration, int price) {
        this.planeId = planeId;
        this.departureId = departureId;
        this.arrivalId = arrivalId;
        this.departureTime = departureTime;
        this.duration = duration;
        this.price = price;
    }
}
