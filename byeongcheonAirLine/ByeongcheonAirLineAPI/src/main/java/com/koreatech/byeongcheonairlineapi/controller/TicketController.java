package com.koreatech.byeongcheonairlineapi.controller;

import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.service.MemberService;
import com.koreatech.byeongcheonairlineapi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TicketController {
    private final TicketService ticketService;
    private final MemberService memberService;
    @Autowired
    public TicketController(TicketService ticketService, MemberService memberService) {
        this.ticketService = ticketService;
        this.memberService = memberService;
    }

    /**
     * 전체 조회
     */
    @GetMapping("tickets")
    public ResponseEntity<Map<String, Object>> tickets() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("tickets", ticketService.findAll());
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 회원 정보로 티켓 조회
     * @param loginDto
     * @return
     */
    @PostMapping("ticket")
    public ResponseEntity<Map<String, Object>> ticket(@RequestBody LoginDto loginDto) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("ticket", ticketService.findByMember(loginDto));
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e){
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 비회원 id로 티켓 조회
     * @param id
     * @return
     */
    @GetMapping("ticket/{id}")
    public ResponseEntity<Map<String, Object>> ticket(@PathVariable int id) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("ticket", ticketService.findByCustomerId(id));
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 티켓 state canceled상태로 변경
     * @param id
     * @return
     */
    @PutMapping("ticket/{id}")
    public ResponseEntity<Map<String, Object>> cancelById(@PathVariable int id) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            ticketService.cacnelById(id);
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 티켓 id로 해당 티켓 삭제
     * @param id
     * @return
     */
    @DeleteMapping("ticket/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable int id) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            ticketService.deleteById(id);
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}