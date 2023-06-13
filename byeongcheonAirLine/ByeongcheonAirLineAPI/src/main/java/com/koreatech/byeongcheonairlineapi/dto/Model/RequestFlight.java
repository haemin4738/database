package com.koreatech.byeongcheonairlineapi.dto.Model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RequestFlight {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
    // 오는날.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;
}
