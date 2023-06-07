package com.koreatech.byeongcheonairlineapi.dto.Model;

import lombok.Data;

import java.util.Date;

@Data
public class FlightModel {
    // 왕복 여부
    private boolean isRoundTrip;
    // 출발지
    private String departure;
    // 도착지
    private String arrival;
    // 동승객 수
    private int passengerNum;
    // 좌석 등급
    private String seatClass;

    // 가는날.
    private Date departureDate;
    // 오는날.
    private Date arrivalDate;
}
