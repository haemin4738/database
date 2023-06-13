package com.koreatech.byeongcheonairlineapi.dto;

import lombok.Data;

@Data
public class CanceledTicketDto {
    private int ticketId;
    private String ticketState;
    private Integer memberId;
    private Integer customerId;
    private int flightRisk;
    private int departureRisk;
    private int arrivalRisk;
    private String departure;
    private String arrival;
    private String departureTime;

}
