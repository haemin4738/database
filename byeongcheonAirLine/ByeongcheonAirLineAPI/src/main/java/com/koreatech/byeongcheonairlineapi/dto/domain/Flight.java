package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Flight {
    private int id;
    private int planeId;
    private int departureId;
    private int arrivalId;
    private Timestamp departureTime;
    private long duration;
    private int price;
    private int riskLevel;
}
