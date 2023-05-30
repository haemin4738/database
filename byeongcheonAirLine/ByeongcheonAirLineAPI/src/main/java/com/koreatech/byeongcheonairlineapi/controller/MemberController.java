package com.koreatech.byeongcheonairlineapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin("*")
public class MemberController {
    /**
     * 회원 가입
     */
    public void signUp() {

    }

    /**
     * 로그인
     */
    public void login() {

    }

    /**
     * 회원 전체 조회
     */
    public void findAll() {

    }

    /**
     * AccessToken 재발급
     */
    public void refresh() {

    }

    /**
     * 로그아웃
     */
    public void logout() {

    }

    /**
     * 테스트
     */
    @RequestMapping("test")
    public ResponseEntity<Map<String, Object>> test() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("message", "SUCCESS");
        } catch(Exception e) {
            resultMap.put("message", "ERROR");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
