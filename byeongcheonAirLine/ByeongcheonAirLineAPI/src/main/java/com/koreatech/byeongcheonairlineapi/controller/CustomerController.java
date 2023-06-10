package com.koreatech.byeongcheonairlineapi.controller;

import com.koreatech.byeongcheonairlineapi.dto.domain.Customer;
import com.koreatech.byeongcheonairlineapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 고객 전체 조회
     */
    @GetMapping("customers")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("customers", customerService.findAll());
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 고객 id 조회
     * @param id
     * @return
     */
    @GetMapping("customer/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap.put("customer", customerService.findById(id));
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * customer 생성
     * @param customer
     * @return
     */
    @PostMapping("customer")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Customer customer) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            customerService.create(customer);
            resultMap.put("message", "SUCCESS!");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("message", "ERROR!");
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
