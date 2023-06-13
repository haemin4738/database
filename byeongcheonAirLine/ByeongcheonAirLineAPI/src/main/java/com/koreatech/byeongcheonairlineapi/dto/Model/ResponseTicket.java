package com.koreatech.byeongcheonairlineapi.dto.Model;

import lombok.Data;

@Data
public class ResponseTicket {
    private int id;
    private String departure;
    private String arrival;
    private String departureTime;
    private String arrivalTime;
    private String seatClass;
    private String planeName;
    private int seatId;
    private String state;
}
