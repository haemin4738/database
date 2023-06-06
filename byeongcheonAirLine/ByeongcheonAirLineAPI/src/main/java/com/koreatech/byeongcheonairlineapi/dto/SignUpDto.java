package com.koreatech.byeongcheonairlineapi.dto;

import com.fasterxml.jackson.databind.DatabindException;
import com.koreatech.byeongcheonairlineapi.dto.domain.Member;
import lombok.Data;

import java.util.Date;

@Data
public class SignUpDto {
    private String account;
    private String password;
    private String password2;
    private String email;
    private String nation;
    private String phone;
    private String enLastName;
    private String enFirstName;
    private String koLastName;
    private String koFirstName;
    private String sex;
    private Date birthday;
}
