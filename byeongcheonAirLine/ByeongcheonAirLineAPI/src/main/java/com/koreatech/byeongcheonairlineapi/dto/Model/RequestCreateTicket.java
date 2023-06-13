package com.koreatech.byeongcheonairlineapi.dto.Model;

import lombok.Data;

import java.util.Date;

@Data
public class RequestCreateTicket {
    //customer
    private String email;
    private String phone;
    private Date birthday;
    private String nation;
    private String sex;
    private String enFirstName;
    private String enLastName;
    private int flightId;
    private int payment;
    private String cardNo;
    private int seatNo;

}
