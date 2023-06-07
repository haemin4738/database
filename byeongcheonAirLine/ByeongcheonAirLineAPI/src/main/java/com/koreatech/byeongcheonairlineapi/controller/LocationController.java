package com.koreatech.byeongcheonairlineapi.controller;

import com.koreatech.byeongcheonairlineapi.service.DataInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LocationController {
    private final DataInputService dataInputService;
    @Autowired
    public LocationController(DataInputService dataInputService) {
        this.dataInputService = dataInputService;
    }
    @GetMapping("test")
    public String String() throws IOException {
    }
}
