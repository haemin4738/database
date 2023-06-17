package com.koreatech.byeongcheonairlineapi.controller;

import com.koreatech.byeongcheonairlineapi.dto.CanceledTicketDto;
import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.RequestCreateTicket;
import com.koreatech.byeongcheonairlineapi.dto.domain.Ticket;
import com.koreatech.byeongcheonairlineapi.service.CancelService;
import com.koreatech.byeongcheonairlineapi.service.MemberService;
import com.koreatech.byeongcheonairlineapi.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin("*")
@Slf4j
@RestController
public class TicketController {
    private final TicketService ticketService;
    private final MemberService memberService;

    private final CancelService cancelService;

    @Autowired
    public TicketController(TicketService ticketService, MemberService memberService, CancelService cancelService) {
        this.ticketService = ticketService;
        this.memberService = memberService;
        this.cancelService = cancelService;
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
     *
     * @param loginDto
     * @return
     */
    @GetMapping("tickets/member")
    public ResponseEntity<Map<String, Object>> ticket(LoginDto loginDto) {
//        log.info("token: {}", request.getHeader("access-token"));
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("tickets", ticketService.findByMember(loginDto));
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            log.error("회원 티켓 조회 에러!!", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 비회원 id로 티켓 조회
     *
     * @param id
     * @return
     */
    @GetMapping("tickets/{id}")
    public ResponseEntity<Map<String, Object>> ticket(@PathVariable int id) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("tickets", ticketService.findByCustomer(id));
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            log.error("비회원 티켓 조회 에러!!", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 티켓 상태 업데이트
     *
     * @param id
     * @param state
     * @return
     */
    @PutMapping("tickets/{id}")
    public ResponseEntity<Map<String, Object>> updateState(@PathVariable int id, @RequestBody String state) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Ticket ticket = new Ticket();
            ticket.setId(id);
            ticket.setState(state);
            ticketService.updateState(ticket);
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 티켓 id로 해당 티켓 삭제
     *
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

    @PostMapping("ticket/customer")
    public ResponseEntity<Map<String, Object>> createCustomerTicket(@RequestBody RequestCreateTicket requestCreateTicket) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            ticketService.createCustomerTicket(requestCreateTicket);
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            log.error("비회원 티켓 생성 에러!!", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("ticket/member")
    public ResponseEntity<Map<String, Object>> createMemberTicket(@RequestBody RequestCreateTicket requestCreateTicket, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            String accessToken = request.getHeader("access-token");
            ticketService.createMemberTicket(requestCreateTicket, accessToken);
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            log.error("회원 티켓 생성에러!!", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("tickets/canceled")
    public ResponseEntity<Map<String, Object>> cancelTicket() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus httpStatus;
        try {
            cancelService.cancelTicket();
            resultMap.put("canceledTickets", cancelService.getCanceledTickets());
            resultMap.put("message", "SUCCESS!");
            httpStatus = HttpStatus.OK;

        } catch (Exception e) {
            log.error("결항 에러!!", e);
            resultMap.put("message", "SERVER ERROR!");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(resultMap, httpStatus);
    }

    @PutMapping("tickets/reset")
    public ResponseEntity<Map<String, Object>> reset() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            cancelService.resetAll();
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);

        }catch (Exception e) {
            resultMap.put("message", "SERVER ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}