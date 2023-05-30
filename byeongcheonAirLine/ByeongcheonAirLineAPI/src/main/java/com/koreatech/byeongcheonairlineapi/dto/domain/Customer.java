package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private int id;
    private String email;
    private String phone;
    private Date birthday;
    private String nation;
    private String sex;
    private String enFirstName;
    private String enLastName;
}
