package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ticket {
    private int id;
    private String state;
    private Timestamp reserveDate;
    private int payment;
    private String cardNo;
    private int memberId;
    private int flightId;
    private int customerId;
    private int boardId;
    private int seatId;
}
