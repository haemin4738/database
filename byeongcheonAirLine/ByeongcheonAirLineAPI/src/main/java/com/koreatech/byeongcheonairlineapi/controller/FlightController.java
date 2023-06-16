package com.koreatech.byeongcheonairlineapi.controller;


import com.koreatech.byeongcheonairlineapi.dto.Model.ResponseFlight;
import com.koreatech.byeongcheonairlineapi.dto.Model.RequestFlight;
import com.koreatech.byeongcheonairlineapi.service.FlightService;
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
@RequestMapping("flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @GetMapping("departure")
    public ResponseEntity<Map<String, Object>> findDepartures(RequestFlight requestFlight) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus httpStatus;
        log.info("request : {}" , requestFlight);
        try {
            List<ResponseFlight> responseFlights = flightService.goTrip(requestFlight);
            if (responseFlights == null || responseFlights.size() == 0) {
                httpStatus = HttpStatus.NO_CONTENT;
                log.info("flights : {}", responseFlights);
            }
            else {
                httpStatus = HttpStatus.OK;
            }
            resultMap.put("flights", responseFlights);
            resultMap.put("message", "SUCCESS!");
        }catch (Exception e) {
            resultMap.put("message", "ERROR!");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("error!", e);
        }
        return new ResponseEntity<>(resultMap, httpStatus);
    }
    @GetMapping("arrival")
    public ResponseEntity<Map<String, Object>> findArrivals(RequestFlight requestFlight) {
        log.info("request : {}" , requestFlight);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus httpStatus;
        try {
            List<ResponseFlight> responseFlights = flightService.comeHome(requestFlight);
            if (responseFlights == null || responseFlights.size() == 0) {
                httpStatus = HttpStatus.NO_CONTENT;
            }
            else {
                httpStatus = HttpStatus.OK;
            }
            resultMap.put("flights", responseFlights);
            resultMap.put("message", "SUCCESS!");
        }catch (Exception e) {
            resultMap.put("message", "ERROR!");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, httpStatus);
    }

}
