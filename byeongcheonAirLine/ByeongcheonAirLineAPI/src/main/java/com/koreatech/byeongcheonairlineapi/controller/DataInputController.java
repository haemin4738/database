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

    @GetMapping("test")
    public void dataTest() {
//        dataInputService.insertPlane();
//        dataInputService.insertSeat();
//        dataInputService.insertFlight();
    }
}
