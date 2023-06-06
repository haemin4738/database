package com.koreatech.byeongcheonairlineapi.controller;

import com.koreatech.byeongcheonairlineapi.service.DataInputService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("data")
public class DataInputController {

    private final DataInputService dataInputService;

    @Autowired
    public DataInputController(DataInputService dataInputService) {
        this.dataInputService = dataInputService;
    }

    /**
     * 초기 Plane, Seat, Flight, Location 데이터 삽입 API
     * 원래는 OPENAPI를 통해 대한항공등의 항공 스케줄 데이터를 받아와서 삽입하는게 바람직함.
     * 이번 플젝은 위 네개의 테이블을 노가다로 완성하고 시작하는 시나리오임.
     */
    @GetMapping("test")
    public void dataTest() {
//        dataInputService.insertPlane();
//        dataInputService.insertSeat();
//        dataInputService.insertFlight();
    }
}
