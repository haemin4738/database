package com.koreatech.byeongcheonairlineapi;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.TimeZone;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ByeongcheonAirLineApiApplication {
    @PostConstruct
    public void started() {
        // timezone KST 셋팅
        TimeZone.setDefault(TimeZone.getTimeZone("KST"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ByeongcheonAirLineApiApplication.class, args);
    }

}
