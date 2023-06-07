package com.koreatech.byeongcheonairlineapi.dto.Model;

import lombok.Data;

@Data
public class FlightModel {
    private boolean isRoundTrip;
    private String departure;
    private String arrival;
    private int passengerNum;
    private String seatClass;

}
