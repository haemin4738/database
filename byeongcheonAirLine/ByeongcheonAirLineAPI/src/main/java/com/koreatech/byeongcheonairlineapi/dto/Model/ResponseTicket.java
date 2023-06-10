package com.koreatech.byeongcheonairlineapi.dto.Model;

import lombok.Data;

@Data
public class ResponseTicket {
    int id;
    String departureLocation;
    String arrivalLocation;
    String departureTime;
    String arrivalTime;
    String seatClass;
    String planeName;
    int seatId;
    String state;
}
