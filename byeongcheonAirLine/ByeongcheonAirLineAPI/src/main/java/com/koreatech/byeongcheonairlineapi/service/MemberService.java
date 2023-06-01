package com.koreatech.byeongcheonairlineapi.service;

import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.dto.SignUpDto;
import com.koreatech.byeongcheonairlineapi.dto.domain.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {

    void signUp(SignUpDto signUpDto);

    List<Member> findAll();

    Map<String, String> login(LoginDto loginDto);

    String refresh(String token);

    void logout(String account);
}
