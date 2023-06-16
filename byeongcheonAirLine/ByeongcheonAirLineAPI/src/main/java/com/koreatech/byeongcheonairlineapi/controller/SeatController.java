package com.koreatech.byeongcheonairlineapi.controller;

import com.koreatech.byeongcheonairlineapi.dto.domain.Plane;
import com.koreatech.byeongcheonairlineapi.dto.domain.Seat;
import com.koreatech.byeongcheonairlineapi.service.PlaneService;
import com.koreatech.byeongcheonairlineapi.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
//@CrossOrigin("*")
@RestController
public class SeatController {
    private final SeatService seatService;
    private final PlaneService planeService;


    @Autowired
    public SeatController(SeatService seatService, PlaneService planeService) {
        this.seatService = seatService;
        this.planeService = planeService;
    }

    @GetMapping("seats/{flightId}")
    public ResponseEntity<Map<String, Object>>getReservedSeat(@PathVariable int flightId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus httpStatus;
        try {
            List<Seat> reservedSeats = seatService.findByFlight(flightId);
            Plane plane = planeService.findByFlightId(flightId);
            httpStatus = HttpStatus.OK;
            resultMap.put("reservedSeats", reservedSeats);
            resultMap.put("planeName", plane.getName());
            resultMap.put("seatPrice", seatService.findPriceByFlight(flightId));
            resultMap.put("message", "SUCCESS!");

        }catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            resultMap.put("message", "ERROR!");
            log.error("ERROR!", e);
        }
        return new ResponseEntity<>(resultMap, httpStatus);
    }
}
