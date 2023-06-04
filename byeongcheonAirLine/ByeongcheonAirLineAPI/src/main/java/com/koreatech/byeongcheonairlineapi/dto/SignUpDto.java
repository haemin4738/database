package com.koreatech.byeongcheonairlineapi.dto;

import com.koreatech.byeongcheonairlineapi.dto.domain.Member;
import lombok.Data;

@Data
public class SignUpDto {
    private Member member;
    private String password2;

}
