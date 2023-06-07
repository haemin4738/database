package com.koreatech.byeongcheonairlineapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class MemberDto {
    private String enLastName;
    private String enFirstName;
    private String sex;
    private String birthday;
    private String phone;
    private String email;

    public MemberDto(String enLastName, String enFirstName, String sex, String birthday, String phone, String email) {
        this.enLastName = enLastName;
        this.enFirstName = enFirstName;
        this.sex = sex;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }
}
