package com.koreatech.byeongcheonairlineapi.controller;


import com.koreatech.byeongcheonairlineapi.dto.Model.FlightModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Slf4j
@RestController
@RequestMapping("flight")
public class FlightController {

    @GetMapping("departure")
    public ResponseEntity<Map<String, Object>> findDepartures(@RequestBody FlightModel flightModel) {
        return null;
    }

}
