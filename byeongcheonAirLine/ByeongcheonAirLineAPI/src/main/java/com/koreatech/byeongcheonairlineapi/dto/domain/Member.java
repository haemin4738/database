package com.koreatech.byeongcheonairlineapi.dto.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Member {
    private int id;
    private String account;
    private String password;


    private String password2;



    private Date birthday;
    private String email;
    private String nation;
    private int milege;
    private String phone;
    private String enLastName;
    private String enFirstName;
    private String koLastName;
    private String koFirstName;
    private String sex;
    private String token;
}
