package com.koreatech.byeongcheonairlineapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ByeongcheonAirLineApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByeongcheonAirLineApiApplication.class, args);
    }

}
